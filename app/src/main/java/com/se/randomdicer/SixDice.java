package com.se.randomdicer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class SixDice {

    private FloatBuffer vertexBuffer;
    private FloatBuffer texBuffer;

    private int sides = 6;
    private int f = 4;
    private int[] images = {
            R.drawable.random_dice_6_1,
            R.drawable.random_dice_6_2,
            R.drawable.random_dice_6_3,
            R.drawable.random_dice_6_4,
            R.drawable.random_dice_6_5,
            R.drawable.random_dice_6_6,
    };
    private int[] textures = new int[sides];
    private Bitmap[] bitmaps = new Bitmap[sides];
    private float halfSize = 1.0f;

    private float [][] rotates;

    public SixDice(Context context) {

        ByteBuffer vbb = ByteBuffer.allocateDirect(12 * f * sides);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();

        for (int side = 0; side < sides; side++) {
            bitmaps[side] = BitmapFactory.decodeStream(
                    context.getResources().openRawResource(images[side]));
            int imageWidth = bitmaps[side].getWidth();
            int imageHeight = bitmaps[side].getHeight();
            float sideWidth = 2.0f;
            float sideHeight = 2.0f;

            if (imageWidth > imageHeight)
                sideHeight = sideHeight * imageHeight / imageWidth;
            else
                sideWidth = sideWidth * imageWidth / imageHeight;

            float sideLeft = -sideWidth / 2;
            float sideRight = -sideLeft;
            float sideTop = sideHeight / 2;
            float sideBottom = -sideTop;

            float[] vertices = {
                    sideLeft,  sideBottom, 0.0f,
                    sideRight, sideBottom, 0.0f,
                    sideLeft,  sideTop,    0.0f,
                    sideRight, sideTop,    0.0f,
            };
            vertexBuffer.put(vertices);
        }
        vertexBuffer.position(0);

        float[] textureCoords = {
                0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f
        };
        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoords.length * f * sides);
        tbb.order(ByteOrder.nativeOrder());
        texBuffer = tbb.asFloatBuffer();
        for (int side = 0; side < sides; side++) {
            texBuffer.put(textureCoords);
        }
        texBuffer.position(0);
    }

    public void draw(GL10 gl) {
        int tf = -f;
        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer);

        // front
        gl.glPushMatrix();
        gl.glTranslatef(0f, 0f, halfSize);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, tf+=f, f);
        gl.glPopMatrix();

        // left
        gl.glPushMatrix();
        gl.glRotatef(270.0f, 0f, 1f, 0f);
        gl.glTranslatef(0f, 0f, halfSize);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[1]);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, tf+=f, f);
        gl.glPopMatrix();

        // back
        gl.glPushMatrix();
        gl.glRotatef(180.0f, 0f, 1f, 0f);
        gl.glTranslatef(0f, 0f, halfSize);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[2]);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, tf+=f, f);
        gl.glPopMatrix();

        // right
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 0f, 1f, 0f);
        gl.glTranslatef(0f, 0f, halfSize);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[3]);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, tf+=f, f);
        gl.glPopMatrix();

        // top
        gl.glPushMatrix();
        gl.glRotatef(270.0f, 1f, 0f, 0f);
        gl.glTranslatef(0f, 0f, halfSize);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[4]);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, tf+=f, f);
        gl.glPopMatrix();

        // bottom
        gl.glPushMatrix();
        gl.glRotatef(90.0f, 1f, 0f, 0f);
        gl.glTranslatef(0f, 0f, halfSize);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[5]);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, tf+=f, f);
        gl.glPopMatrix();

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    public void loadTexture(GL10 gl) {
        gl.glGenTextures(sides, textures, 0);

        for (int side = 0; side < sides; side++) {
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[side]);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmaps[side], 0);
            bitmaps[side].recycle();
        }
    }

}
