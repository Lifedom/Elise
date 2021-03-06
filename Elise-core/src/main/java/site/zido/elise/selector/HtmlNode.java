package site.zido.elise.selector;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * html节点
 *
 * @author zido
 */
public class HtmlNode extends AbstractSelectable {

    private final List<Element> elements;

    public HtmlNode(List<Element> elements) {
        this.elements = elements;
    }

    public HtmlNode() {
        elements = null;
    }

    protected List<Element> getElements() {
        return elements;
    }

    @Override
    public Selectable links() {
        return selectElements(new LinkSelector());
    }

    @Override
    public Selectable links(List<LinkProperty> properties) {
        return selectElements(new LinkSelector(properties));
    }

    @Override
    public Selectable xpath(String xpath) {
        XpathSelector xpathSelector = Selectors.xpath(xpath);
        return selectElements(xpathSelector);
    }

    @Override
    public Selectable selectList(Selector selector) {
        if (selector instanceof AbstractElementSelector) {
            return selectElements((AbstractElementSelector) selector);
        }
        return selectList(selector, getSourceTexts());
    }

    @Override
    public Selectable select(Selector selector) {
        return selectList(selector);
    }

    /**
     * select elements
     *
     * @param elementSelector elementSelector
     * @return result
     */
    protected Selectable selectElements(AbstractElementSelector elementSelector) {
        ListIterator<Element> elementIterator = getElements().listIterator();
        if (!elementSelector.isText()) {
            List<Element> resultElements = new ArrayList<>();
            while (elementIterator.hasNext()) {
                Element element = checkElementAndConvert(elementIterator);
                List<Element> selectElements = elementSelector.selectElements(element);
                resultElements.addAll(selectElements);
            }
            return new HtmlNode(resultElements);
        } else {
            // has attribute, consider as plaintext
            List<String> resultStrings = new ArrayList<>();
            while (elementIterator.hasNext()) {
                Element element = checkElementAndConvert(elementIterator);
                List<String> selectList = elementSelector.selectList(element);
                resultStrings.addAll(selectList);
            }
            return new PlainText(resultStrings);

        }
    }

    /**
     * Only document can be select
     * See: https://github.com/code4craft/webmagic/issues/113
     *
     * @param elementIterator elementIterator
     * @return element element
     */
    private Element checkElementAndConvert(ListIterator<Element> elementIterator) {
        Element element = elementIterator.next();
        if (!(element instanceof Document)) {
            Document root = new Document(element.ownerDocument().baseUri());
            Element clone = element.clone();
            root.appendChild(clone);
            elementIterator.set(root);
            return root;
        }
        return element;
    }

    @Override
    public Selectable css(String selector) {
        CssSelector cssSelector = Selectors.css(selector);
        return selectElements(cssSelector);
    }

    @Override
    public Selectable css(String selector, String attrName) {
        CssSelector cssSelector = Selectors.css(selector, attrName);
        return selectElements(cssSelector);
    }

    @Override
    public List<Selectable> nodes() {
        List<Selectable> selectables = new ArrayList<>();
        for (Element element : getElements()) {
            List<Element> childElements = new ArrayList<>(1);
            childElements.add(element);
            selectables.add(new HtmlNode(childElements));
        }
        return selectables;
    }

    @Override
    protected List<String> getSourceTexts() {
        List<String> sourceTexts = new ArrayList<>(getElements().size());
        for (Element element : getElements()) {
            sourceTexts.add(element.html());
        }
        return sourceTexts;
    }
}
