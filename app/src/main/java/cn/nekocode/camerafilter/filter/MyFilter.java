package cn.nekocode.camerafilter.filter;

import android.content.Context;
import android.opengl.GLES20;

import cn.nekocode.camerafilter.MyGLUtils;
import cn.nekocode.camerafilter.R;

/**
 * Created by prada on 2/7/17.
 */

public class MyFilter extends CameraFilter {
    private final int program;
    private final int texture2Id;
    private final int texture2Id2;

    // 2 texture + camera
    public MyFilter(Context context) {
        super(context);

        // Build shaders
        program = MyGLUtils.buildProgram(context, R.raw.vertext, R.raw.my);

        texture2Id = MyGLUtils.loadTexture(context, R.raw.test, new int[2]);
        texture2Id2 = MyGLUtils.loadTexture(context, R.raw.test1, new int[2]);
    }

    @Override
    void onDraw(int cameraTexId, int canvasWidth, int canvasHeight) {
        // TODO
        long currentTime = System.currentTimeMillis() - START_TIME;

        long tmp = currentTime % 6000;
        int c1;
        int c2;
        if (tmp <= 2000) {
            c1 = texture2Id;
            c2 = cameraTexId;
        } else if (tmp <= 4000) {
            c1 = texture2Id2;
            c2 = texture2Id;
        } else {
            c1 = cameraTexId;
            c2 = texture2Id2;
        }

        setupShaderInputs(program,
            new int[]{canvasWidth, canvasHeight},
            new int[]{c1, c2},
            new int[][]{});
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
