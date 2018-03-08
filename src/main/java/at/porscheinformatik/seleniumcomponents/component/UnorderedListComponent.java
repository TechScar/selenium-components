/**
 *
 */
package at.porscheinformatik.seleniumcomponents.component;

import static at.porscheinformatik.seleniumcomponents.WebElementSelector.*;

import java.util.function.Predicate;

import at.porscheinformatik.seleniumcomponents.AbstractSeleniumComponent;
import at.porscheinformatik.seleniumcomponents.AbstractSeleniumContainer;
import at.porscheinformatik.seleniumcomponents.SeleniumComponent;
import at.porscheinformatik.seleniumcomponents.SeleniumComponentFactory;
import at.porscheinformatik.seleniumcomponents.SeleniumComponentList;
import at.porscheinformatik.seleniumcomponents.SeleniumGlobals;
import at.porscheinformatik.seleniumcomponents.SeleniumUtils;
import at.porscheinformatik.seleniumcomponents.WebElementSelector;

/**
 * @author Daniel Furtlehner
 * @param <EntryT> type of entry in the list
 */
public class UnorderedListComponent<EntryT extends AbstractSeleniumComponent> extends AbstractSeleniumContainer<EntryT>
{
    public UnorderedListComponent(SeleniumComponent parent, SeleniumComponentFactory<EntryT> componentFactory)
    {
        this(parent, selectByTagName("ul"), componentFactory);
    }

    public UnorderedListComponent(SeleniumComponent parent, WebElementSelector selector,
        SeleniumComponentFactory<EntryT> componentFactory)
    {
        super(parent, selector, selectByTagName("li"), componentFactory);
    }

    public SeleniumComponentList<EntryT> getItems()
    {
        return findAllChilds();
    }

    public boolean containsItem(Predicate<EntryT> predicate)
    {
        return SeleniumUtils
            .optional(
                () -> SeleniumUtils.keepTrying(SeleniumGlobals.getShortTimeoutInSeconds(), () -> findChild(predicate)))
            .isPresent();
    }

    public SeleniumComponentList<EntryT> getVisibleItems()
    {
        return getItems().filter($ -> $.isVisible());
    }

}
