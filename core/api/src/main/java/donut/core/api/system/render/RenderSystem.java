package donut.core.api.system.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import donut.core.api.GameSystem;
import donut.core.api.system.physic.PhysicSystem;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.Entity;
import donut.core.wrapper.RESquest.IConsumeCallback;
import donut.core.wrapper.RESquest.ResourceRequest;

public class RenderSystem extends GameSystem implements IConsumeCallback {
    public final SpriteBatch batch;
    public final ShapeRenderer shapeRenderer;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private PhysicSystem physicSystem;
    public final OrthographicCamera camera;

    public RenderSystem()
    {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(320, 240);
        getConsumer().postRequest(new ResourceRequest(PhysicSystem.class, RenderSystem.class, this));
    }

    @Override
    public void onUpdate(EntitiesContainer entitiesContainer, Float deltaTime) {
        for(Entity entity : entitiesContainer.getEntitiesFor(Renderer.class))
        {
            Renderer renderer = entity.getComponent(Renderer.class);

            for(IRenderCallback renderCallback : renderer.getRenderCallBacks())
            {
                renderCallback.render(deltaTime, batch, shapeRenderer);
            }
        }

        debugRenderer.render(physicSystem.world, camera.combined);
    }

    @Override
    public boolean consume(Object resource) {
        if(resource instanceof PhysicSystem)
        {
            physicSystem = (PhysicSystem) resource;
            return true;
        }
        return false;
    }
}
