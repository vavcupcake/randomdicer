package com.se.randomdicer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class Renderer implements GLSurfaceView.Renderer {
    private SixDice dice;
    private static float angle = 0;
    private static float speed = 4.5f;

    public Renderer(Context context) {
        dice = new SixDice(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);

        dice.loadTexture(gl);
        gl.glEnable(GL10.GL_TEXTURE_2D);
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        height = (height == 0) ? 1 : height;
        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }


    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -7.0f);
        gl.glRotatef(angle, 0.15f, 0.2f, 0.3f);
        dice.draw(gl);

        angle += speed;
    }

}
