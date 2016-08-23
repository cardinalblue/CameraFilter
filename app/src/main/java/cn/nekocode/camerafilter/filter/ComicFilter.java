package cn.nekocode.camerafilter.filter;

import android.content.Context;
import android.opengl.GLES20;

import cn.nekocode.camerafilter.MyGLUtils;
import cn.nekocode.camerafilter.R;

/**
 * Created by prada on 8/22/16.
 */
public class ComicFilter extends CameraFilter {
    private final int program;

    public ComicFilter(Context context) {
        super(context);
        // Build shaders
        program = MyGLUtils.buildProgram(context, R.raw.vertext, R.raw.comic);
    }

    @Override
    void onDraw(int cameraTexId, int canvasWidth, int canvasHeight) {
        setupShaderInputs(program,
            new int[]{canvasWidth, canvasHeight},
            new int[]{cameraTexId},
            new int[][]{});
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
