/*
 * Copyright 2014 Julian Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antran.expressfootball.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.antran.expressfootball.R;
import com.squareup.picasso.Transformation;

/**
 * Created by julian on 13/6/21.
 */
public class CircleTransform implements Transformation {
	@Override
	public Bitmap transform(Bitmap source) {
		int size = Math.min(source.getWidth(), source.getHeight());

		int x = (source.getWidth() - size) / 2;
		int y = (source.getHeight() - size) / 2;

		Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);

		Config config = source.getConfig();
		if (config == null) {
			config = Config.ARGB_8888;
		}

		Bitmap bitmap = Bitmap.createBitmap(size, size, config);
		if (squaredBitmap != source) {
			source.recycle();
		}

		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();

		BitmapShader shader = new BitmapShader(squaredBitmap,
				BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
		paint.setShader(shader);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setColor(Color.parseColor("#422a30"));

		final Rect rect = new Rect(0, 0, size, size);
		final RectF rectF = new RectF(rect);
		canvas.drawOval(rectF, paint);

		squaredBitmap.recycle();
		return bitmap;
	}

	@Override
	public String key() {
		return "circle";
	}
}