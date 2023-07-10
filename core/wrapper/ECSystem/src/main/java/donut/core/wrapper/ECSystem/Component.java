package donut.core.wrapper.ECSystem;

import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;

public class Component implements IResourceConsumer {
    ResourceConsumer consumer = new ResourceConsumer();

    @Override
    public ResourceConsumer getConsumer() {
        return consumer;
    }
}
