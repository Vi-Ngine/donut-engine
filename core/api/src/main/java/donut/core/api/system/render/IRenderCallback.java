package donut.core.api.system.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface IRenderCallback
{
    public void render(float deltaTime, SpriteBatch batch, ShapeRenderer shapeRenderer);
}
