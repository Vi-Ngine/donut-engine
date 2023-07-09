package donut.core.api.system.physic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import donut.core.wrapper.ECSystem.Component;
import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.IResourceConsumer;
import donut.core.wrapper.RESquest.ResourceRequest;

public class PhysicBody extends Component implements IResourceConsumer
{
    private World world;
    private BodyDef bodyDef;
    private Body body;

    @Override
    public ResourceRequest[] getRequests()
    {
        return new ResourceRequest[]
                {
                    new ResourceRequest(World.class, new IConsumeCallback<World>() {
                        @Override
                        public boolean consume(World world) {
                            PhysicBody.this.world = world;
                            PhysicBody.this.body = world.createBody(bodyDef);
                            return true;
                        }
                    })
                };
    }

    PhysicBody(BodyDef bodyDef)
    {
        this.bodyDef = bodyDef;
    }
}
