import donut.core.wrapper.RESquest.ResourceProvider;
import org.junit.platform.commons.logging.Logger;

public class IntProvider extends ResourceProvider {
    public IntProvider()
    {
        addResource(123);
    }
}
