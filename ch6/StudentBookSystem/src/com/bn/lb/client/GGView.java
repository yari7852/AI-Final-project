package com.bn.lb.client;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GGView extends View 
{
	RootActivity activity;
	int COMPONENT_WIDTH;
	int COMPONENT_HEIGHT;
	boolean initflag=false;	
	Bitmap[] bma;	
	Paint paint;
	int[] drawablesId;
	int currIndex=0;
	boolean workFlag=true;
	
	public GGView(Context father,AttributeSet as) 
	{ 
		super(father,as);	
		this.drawablesId=new int[]
		{
			R.drawable.adv2,	
			R.drawable.adv1,					
			R.drawable.adv3,				 
		};
		bma=new Bitmap[drawablesId.length];
		initBitmaps();
		paint=new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);//�������		
		
		new Thread()
		{
			public void run()
			{
				while(workFlag)
				{
					currIndex=(currIndex+1)%drawablesId.length;
					GGView.this.postInvalidate();
					try 
					{
						Thread.sleep(3000);
					} catch (InterruptedException e) 
					{						
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void initBitmaps()
	{
		Resources res=this.getResources();
		for(int i=0;i<drawablesId.length;i++)
		{
			bma[i]=BitmapFactory.decodeResource(res, drawablesId[i]);
		}
	}
	
	public void onDraw(Canvas canvas)
	{
		if(!initflag)
		{
			COMPONENT_WIDTH=this.getWidth();//��ȡview�Ŀ��
			COMPONENT_HEIGHT=this.getHeight();//��ȡview�ĸ߶�
			initflag=true;
		}
		
		int picWidth=bma[currIndex].getWidth();
		int picHeight=bma[currIndex].getHeight();
		
		int startX=(COMPONENT_WIDTH-picWidth)/2;
		int startY=(COMPONENT_HEIGHT-picHeight)/2;
		
		//���Ʊ���ɫ
		canvas.drawARGB(255, 255,204, 128);		
		canvas.drawBitmap(bma[currIndex], startX,startY, paint);
		
	}
}
