package donut.core.api.system.event;
import donut.core.api.GameComponent;
import donut.core.wrapper.ECSystem.Component;

public class EntityCallbackReceiver extends GameComponent {
    private IUpdateCallback update;
    private ICreateCallback create;

    public void onUpdate(float delta)
    {
        update.onUpdate(delta);
    }

    public void onCreate()
    {
        create.onCreate();
    }

    public EntityCallbackReceiver setUpdater(IUpdateCallback updater)
    {
        this.update = updater;
        return this;
    }

    public EntityCallbackReceiver setCreator(ICreateCallback creater)
    {
        this.create = creater;
        return this;
    }
}
