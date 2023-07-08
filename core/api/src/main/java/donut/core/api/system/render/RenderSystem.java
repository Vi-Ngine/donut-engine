package donut.core.api.system.render;

import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import donut.core.api.GameEntity;
import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.api.system.physic.PhysicSystem;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.EntitySystem;

public class RenderSystem extends EntitySystem {
    public final SpriteBatch batch;
    public final ShapeRenderer shapeRenderer;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    public final OrthographicCamera camera;

    public RenderSystem()
    {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(320, 240);
    }

    @Override
    public void onUpdate(EntitiesContainer entitiesContainer) {
        for(GameEntity entity :
                RenderSystem.this.getEngine().getEntitiesFor(Family.all(Renderer.Wrapable.class).get()))
        {
            Renderer renderer = entity.getComponent(Renderer.class);

            for(IRenderCallback renderCallback : renderer.getRenderCallBacks())
            {
                renderCallback.render(deltaTime, batch, shapeRenderer);
            }
        }

        debugRenderer.render(
                RenderSystem.this.getEngine().getSystem(PhysicSystem.class).getWorld(),
                camera.combined);
    }
}
