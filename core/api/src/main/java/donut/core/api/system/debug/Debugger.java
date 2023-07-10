package donut.core.api.system.debug;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import donut.core.api.GameEntity;
import donut.core.api.system.render.IRenderCallback;
import donut.core.wrapper.ECSystem.Component;

public class Debugger extends Component implements IRenderCallback {
    private boolean hasRenderer = false;
    public boolean hasRenderer()
    {
        return hasRenderer;
    }

    @Override
    public void render(float deltatime, SpriteBatch batch, ShapeRenderer shapeRenderer)
    {
        hasRenderer = true;

        BitmapFont font = new BitmapFont();
        batch.begin();
        font.draw(batch, debugText, gameEntity.transform.px, gameEntity.transform.py);
        batch.end();
    }

    public String debugText = "Debug me!";
}
