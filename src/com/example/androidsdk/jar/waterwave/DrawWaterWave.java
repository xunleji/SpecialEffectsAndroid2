/**
 * fengsheng.yang@live.cn
 * 本程序可以随便使用，可以随便转载，但是希望尊重我的成果，请挂上本人博客
 * http://huojv.blog.hexun.com(有时间去看看，也许会有你要的东西)
 * 我老婆马艳，我会对你很好的！谢谢理解!
 * 2008-12-25
 */
package com.example.androidsdk.jar.waterwave;

import com.example.androidsdk.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class DrawWaterWave extends View implements Runnable {

	boolean bRunning = false;
	int BACKWIDTH;
	int BACKHEIGHT;
	short[] buf2;
	short[] buf1;
	int[] Bitmap2;
	int[] Bitmap1;

	public DrawWaterWave(Context context) {
		super(context);
		Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg);
		BACKWIDTH = image.getWidth();
		BACKHEIGHT = image.getHeight();

		buf2 = new short[BACKWIDTH * BACKHEIGHT];
		buf1 = new short[BACKWIDTH * BACKHEIGHT];
		Bitmap2 = new int[BACKWIDTH * BACKHEIGHT];
		Bitmap1 = new int[BACKWIDTH * BACKHEIGHT];

		image.getPixels(Bitmap1, 0, BACKWIDTH, 0, 0, BACKWIDTH, BACKHEIGHT);

		start();
	}

	void DropStone(int x,// x坐标
			int y,// y坐标
			int stonesize,// 波源半径
			int stoneweight)// 波源能量
	{
		if ((x + stonesize) > BACKWIDTH || (y + stonesize) > BACKHEIGHT ||
				(x - stonesize) < 0 || (y - stonesize) < 0)
			return;
		for (int posx = x - stonesize; posx < x + stonesize; posx++)
			for (int posy = y - stonesize; posy < y + stonesize; posy++)
				if ((posx - x) * (posx - x) + (posy - y) * (posy - y) < stonesize * stonesize)
					buf1[BACKWIDTH * posy + posx] = (short) -stoneweight;
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(Bitmap2, 0, BACKWIDTH, 0, 0, BACKWIDTH, BACKHEIGHT, false, null);
	}

	public void start() {
		bRunning = true;
		Thread t = new Thread(this);
		t.start();
	}

	public void key() {
//		DropStone(BACKWIDTH / 2, BACKHEIGHT / 2, 10, 50);
	}

	public void stop() {
		bRunning = false;
	}

	public void run() {

		while (bRunning) {
			// try
			// {
			// Thread.sleep(50);
			// }catch(Exception e){e.printStackTrace();};
			RippleSpread();
			render();
			postInvalidate();
			DropStone(BACKWIDTH / 2, BACKHEIGHT / 2, 10, 50);
		}
	}

	void RippleSpread() {
		for (int i = BACKWIDTH; i < BACKWIDTH * BACKHEIGHT - BACKWIDTH; i++) {
			// 波能扩散
			buf2[i] = (short) (((buf1[i - 1] + buf1[i + 1] + buf1[i - BACKWIDTH] + buf1[i + BACKWIDTH]) >> 1) - buf2[i]);
			// 波能衰减
			buf2[i] -= buf2[i] >> 5;
		}
		// 交换波能数据缓冲区
		short[] ptmp = buf1;
		buf1 = buf2;
		buf2 = ptmp;
	}

	void render() {
		int xoff, yoff;
		int k = BACKWIDTH;
		for (int i = 1; i < BACKHEIGHT - 1; i++) {
			for (int j = 0; j < BACKWIDTH; j++) {
				// 计算偏移量
				xoff = buf1[k - 1] - buf1[k + 1];
				yoff = buf1[k - BACKWIDTH] - buf1[k + BACKWIDTH];

				// 判断坐标是否在窗口范围内
				if ((i + yoff) < 0) {
					k++;
					continue;
				}
				if ((i + yoff) > BACKHEIGHT) {
					k++;
					continue;
				}
				if ((j + xoff) < 0) {
					k++;
					continue;
				}
				if ((j + xoff) > BACKWIDTH) {
					k++;
					continue;
				}

				// 计算出偏移象素和原始象素的内存地址偏移量
				int pos1, pos2;
				pos1 = BACKWIDTH * (i + yoff) + (j + xoff);
				pos2 = BACKWIDTH * i + j;
				Bitmap2[pos2++] = Bitmap1[pos1++];
				k++;
			}
		}
	}

}
