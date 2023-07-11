package donut.core.api.system.physic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import wrapper.ecsystem.GameComponent;
import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceConsumer;
import donut.core.wrapper.RESquest.ResourceRequest;

public class PhysicBody extends GameComponent implements IResourceConsumer
{
    private World world;
    private BodyDef bodyDef;
    private Body body;

    private ResourceConsumer consumer = new ResourceConsumer();

    PhysicBody()
    {
        this(new BodyDef()
        {
            {
                type = BodyType.StaticBody;
            }
        });
    }

    PhysicBody(BodyDef bodyDef)
    {
        this.bodyDef = bodyDef;
        consumer.postRequest(new ResourceRequest<World>(World.class, new IConsumeCallback<World>() {
            @Override
            public boolean consume(World world) {
                PhysicBody.this.world = world;
                body = world.createBody(bodyDef);
                return true;
            }
        }));
    }

    @Override
    public ResourceConsumer getConsumer() {
        return consumer;
    }
}
