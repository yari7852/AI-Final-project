package com.bn.lb.admin;

import static com.bn.lb.admin.Constant.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

enum WhichView {MAIN_MENU,DENGLU_VIEW,BOOKMAIN_VIEW,BOOKMANAGEMENT_INSERTVIEW,BOOKMANAGEMENT_INSERTVIEW_XIANSHI,
	BOOKMANAGEMENT_SELECT,BOOKMANAGEMENT_MODIFY,BOOKMANAGERMENT_DELETE,STUMAIN_VIEW,STUADD_VIEW,STUSELECT_VIEW,
	STUMOFIDY_VIEW,ADMINMAIN_VIEW,ADMINSELECT_VIEW,ADMINMOFIDY_VIEW,FEE_VIEW,ADMINADD_VIEW,HELP_VIEW,ABOUT_VIEW,
	LOSE_DETAILS_VIEW,LOSE_INFO_VIEW,LOSE_VIEW,QUERYMAIN_VIEW,SELF_VIEW_INFO,QUERY_VIEW,GIRD_VIEW,DETIALSVIEW,FEE_DRTILED,
	FEE_XIANSHI,YUYUE_VIEW,YUYUEDETAILED_VIEW}

public class RootActivity extends Activity 
{
	MainMenuView mmv;
	WhichView curr;
	static int EditTextId;
	private int permitted;  //用於權限的驗證  
	private String temp[]=new String[10];
	private String temp2[]=new String[10];
	private String temp3[]=new String[9];
	private String temp4[]=new String[3];
	private String temp5[]=new String[8];
	private String temp6[]=new String[3];  //超期天數
	private String temp7[]=new String[7];
	private String yuyuetemp;
	private String stunotemp;
	private String tempp;
	private String tempp1;
	private String isbnnumber;
	private String datetemp;
	public Vector<String> selectTOgird =new Vector<String>();
	private  String temp1;  
	private Vector<String> queryTOgird =new Vector<String>();	
	private Vector<String> detailsSelect=new Vector<String>();	
	private Vector<Integer> resultnumdetails =new Vector<Integer>();
	private Vector<String> loseInfo=new Vector<String>();//掛失圖書基本信息的數據集合
	private Vector<String> loseInfo1=new Vector<String>();//掛失圖書基本信息的數據集合
	private String SnameID;

    
    private Vector<String> feetodetailed=new Vector<String>();
    Dialog dateInputdialog;
    static String strTime;
	
	Handler hd=new Handler() //接受信息界面跳轉
    {
	   @Override
	  public void handleMessage(Message msg)//重寫方法  
	  {
		 switch(msg.what)
		 {
		     case 0:
		    	 gotoDengLuView();  
			    break;
		 }
	 }
   };	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);        
        //設置全屏顯示
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //強制為橫屏
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //強制為豎屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        goToWelcomeView();
        
    }
    //歡迎界面
    public void goToWelcomeView()
    {
    	MySurfaceView mView=new MySurfaceView(this);
    	setContentView(mView);
    }
    //選項菜單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	MenuItem help=menu.add(MAIN_GROUP,MENU_GENDER_HELP,0,R.string.help); 
    	help.setIcon(R.drawable.help); 
    	help.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{
					RootActivity.this.goToHelpView();
					return false;
				} 
    		} 
    	);
    	MenuItem about=menu.add(MAIN_GROUP,MENU_GENDER_ABOUT,0,R.string.about); 
    	about.setIcon(R.drawable.about);
    	about.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener() 
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{
					RootActivity.this.goToAboutView();
					return false;
				} 
    		}
    	);
    	return true;
    }  
    //創建對話框
    @Override
    public Dialog onCreateDialog(int id)   
    {    	
        Dialog result=null;
    	switch(id)
    	{
	    	
	    	case DATE_INPUT_DIALOG_ID:
	    		dateInputdialog=new MyDialog(this,R.layout.dialog_date_input);
	    		result=dateInputdialog;
	    	break;
    	}   
		return result;
    }
    @Override
    public void onPrepareDialog(int id, final Dialog dialog)
    {
    	//若不是等待對話框則返回
    	switch(id)
    	{
 	     
    	   //第二個窗口
    	   case DATE_INPUT_DIALOG_ID:
    		   //第一個按鈕
    		   ImageButton button1=(ImageButton)dateInputdialog.findViewById(R.id.ImageButton01);
    		   final EditText et1=(EditText)dateInputdialog.findViewById(R.id.EditText01);
    		   this.UpOrDownDateTime(button1, et1, UP_TIME);
    		   //第二個按鈕
    		   ImageButton button2=(ImageButton)dateInputdialog.findViewById(R.id.ImageButton02);
    		   this.UpOrDownDateTime(button2, et1, DOWN_TIME);
    		   //第三個按鈕
    		   ImageButton button3=(ImageButton)dateInputdialog.findViewById(R.id.ImageButton03);
    		   final EditText et2=(EditText)dateInputdialog.findViewById(R.id.EditText02);
    		   this.UpOrDownDateTime(button3, et2, UP_TIME);
    		   //第四個按鈕
    		   ImageButton button4=(ImageButton)dateInputdialog.findViewById(R.id.ImageButton04);
    		   this.UpOrDownDateTime(button4, et2, DOWN_TIME);
    		   //第五個按鈕
    		   ImageButton button5=(ImageButton)dateInputdialog.findViewById(R.id.ImageButton05);
    		   final EditText et3=(EditText)dateInputdialog.findViewById(R.id.EditText03);
    		   this.UpOrDownDateTime(button5, et3, UP_TIME);
    		   //第四個按鈕
    		   ImageButton button6=(ImageButton)dateInputdialog.findViewById(R.id.ImageButton06);
    		   this.UpOrDownDateTime(button6, et3, DOWN_TIME);
    		   
    		   //確定按鈕
    		   Button bsure=(Button)dateInputdialog.findViewById(R.id.Button01);
    		   bsure.setOnClickListener
    		   (
    				new OnClickListener()
    				{
						@Override
						public void onClick(View v) 
						{
							//年
							EditText eyear=(EditText)dateInputdialog.findViewById(R.id.EditText01);
							String syear=eyear.getText().toString();
							//月
							EditText emonth=(EditText)dateInputdialog.findViewById(R.id.EditText02);
							String smonth=emonth.getText().toString();
							//日
							EditText edate=(EditText)dateInputdialog.findViewById(R.id.EditText03);
							String sdate=edate.getText().toString();
							//總的
							String str=DateUtil.getdate(syear, smonth, sdate, YEAR_INTERVAL);   //驗證日期格式並返回
							//彈出Toast
							//hgfddsdsssssss
							if(str==null)
							{
								switch(DateUtil.ERROR_MSG_INT)
								{
								     case 0:
								    	 Toast.makeText
								    	 (
								    			 RootActivity.this, 
								    			 "年份太久遠", 
								    			 Toast.LENGTH_LONG
								    	  ).show(); 
								     break;
								     case 1:
								    	 Toast.makeText
								    	 (
								    			 RootActivity.this, 
								    			 "二月的天數不對", 
								    			 Toast.LENGTH_LONG
								    	  ).show();
								     break;
								     case 2:
								    	 Toast.makeText
								    	 (
								    			 RootActivity.this, 
								    			 "出錯", 
								    			 Toast.LENGTH_LONG
								    	 ).show();
								     break;
								     case 3:
								    	 Toast.makeText
								    	 (
								    			 RootActivity.this, 
								    			 "年份或月份或日期格式不對！請您檢查清楚！", 
								    			 Toast.LENGTH_LONG
								    	  ).show();
								     break;
								}
							}
							//此處改動
							else
							{
								EditText  dateEdit01=(EditText )findViewById(R.id.bookbuttimeedit);	
								dateEdit01.setText(str);
								dateInputdialog.cancel();
							}
						}
    				}
    		   );
    		   Button bcancel=(Button)dateInputdialog.findViewById(R.id.Button02); // 返回按鈕監聽
    		   this.cancelDialog(bcancel, dateInputdialog);
    	   break;
    	}
    }   
    
    //向上或向下按鍵
    public void UpOrDownDateTime(ImageButton button,final EditText et,final int uptime)
    {
    	button.setOnClickListener
    	(
    		new OnClickListener()
    		{
    			@Override
    			public void onClick(View v)
    			{
    				String etStr=et.getText().toString();
    				int etInt=Integer.parseInt(etStr)+uptime;
    				String str=etInt+"";
    				et.setText(str);
    			}
    		}
    	);
    }
    
    public void cancelDialog(Button button,final Dialog dialog)  //銷毀對話框方法
    {
    	button.setOnClickListener
    	(
    		new OnClickListener()
    		{
    			@Override
    			public void onClick(View v)
    			{
    				dialog.cancel();
    			}
    		}
    	);
    }
    
    
    public void goToMainMenu()
    {
    	if(mmv==null)
    	{
    		mmv=new MainMenuView(this);
    	}
    	
    	
    	setContentView(mmv);
    	
    	curr=WhichView.MAIN_MENU;
    }
 
    public void gotoloseView()
    {
    	setContentView(R.layout.lose); 
    	final EditText tvXH=(EditText)RootActivity.this.findViewById(R.id.loseXH);
    	final Button loseButtonOk=(Button)RootActivity.this.findViewById(R.id.losebok);
    	final Button loseButtonRe=(Button)RootActivity.this.findViewById(R.id.loseresert);
    	//為後退按鈕添加監聽
    	ImageButton imagelose=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonlose);
    	imagelose.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	//重置按鈕的設置監聽
    	loseButtonRe.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					tvXH.setText("");
				
				}
    			
    		}
    	);
    	loseInfo.clear();
    	//掛失按鈕的設置監聽
    	loseButtonOk.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SnameID=tvXH.getText().toString().trim();
					loseInfo=DBUtil.getSomeInfo(SnameID);
					gotoloseinfoView();
				}
    			
    		}
    	);
    	   	
    	curr=WhichView.LOSE_VIEW;
    			
		 
    }
    
    public void gotoloseinfoView()
    {
    	setContentView(R.layout.loseinfo);
    	
    	GridView gvlose=(GridView)RootActivity.this.findViewById(R.id.GridViewlose01);
    	ImageButton imagelose=(ImageButton)RootActivity.this.findViewById(R.id.ImageButton_loseinfo1);
    	imagelose.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          generateDataList2(loseInfo), //數據List
          R.layout.lose_grid_row, //行對應layout id
          new String[]{"col1","col2","col3","col4","col5"}, //列名列表
          new int[]{R.id.loseTextViewbooknumber,R.id.loseTextViewbookisbn,R.id.loseTextViewbookname,R.id.loseTextViewbookauthor,R.id.loseTextViewpress}//列對應控件id列表
        );
       
    	gvlose.setAdapter(sca);//為GridView設置數據適配器        
        //設置選項選中的監聽器
    	gvlose.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//重寫選項被選中事件的處理方法
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        );  
        
        //設置選項被單擊的監聽器
    	gvlose.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//重寫選項被單擊事件的處理方法				
				LinearLayout l1=(LinearLayout)arg1;//獲取當前選中選項對應的LinearLayout
				LinearLayout l2=(LinearLayout)l1.getChildAt(0);
				TextView tvn=(TextView)l2.getChildAt(1);//獲取其中的TextView 書號
				String booknum=tvn.getText().toString().trim();
				loseInfo1=DBUtil.getBNSomeInfo(booknum);
				gotolosedetailsView();
			}        	   
           }
        );        
    	//為後退的按鈕添加監聽
    	ImageButton imageibt=(ImageButton)RootActivity.this.findViewById(R.id.ImageButton_loseinfo1);
    	imageibt.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	curr=WhichView.LOSE_INFO_VIEW;
    }
    //掛失界面的詳細界面
    public void gotolosedetailsView()
    {
    	setContentView(R.layout.lose_details_info);
    	
    	
    	final TextView tv1=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView02);
    	final TextView tv2=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView04);
    	final TextView tv3=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView06);
    	TextView tv4=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView08);
    	TextView tv5=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView10);
    	TextView tv6=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView12);
    	TextView tv7=(TextView)RootActivity.this.findViewById(R.id.lose_detailTextView14);
    	tv1.setText(loseInfo1.get(0));    	
    	tv2.setText(loseInfo1.get(3));
    	tv3.setText(loseInfo1.get(4));
    	tv4.setText(loseInfo1.get(5));
    	tv5.setText(loseInfo1.get(6));
    	tv6.setText(loseInfo1.get(1));
    	tv7.setText(loseInfo1.get(2));
    	ImageButton imagelose=(ImageButton)RootActivity.this.findViewById(R.id.details_ImageButtonlose);
    	imagelose.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
        
    	
    	//掛失按鈕的監聽
    	Button bt=(Button)RootActivity.this.findViewById(R.id.lose_details_button);
    	bt.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					String[] losebook=new String[2];					
					losebook[0]=tv1.getText().toString().trim();
			    	losebook[1]=tv3.getText().toString().trim();
			    	int num=DBUtil.getMaxGSBH()+1;
			    	String sql="insert into losebook values ("+num+",'"+losebook[0]+"','"+losebook[1]+"','"+SnameID+"')";
			    	DBUtil.update(sql);
			    	Toast.makeText
					(
							RootActivity.this,
							"掛失成功！", 
							Toast.LENGTH_SHORT
					).show();
				}
    			
    		}
    	);
      //後退按鈕的設置監聽
    	ImageButton imageb=(ImageButton)RootActivity.this.findViewById(R.id.details_ImageButtonlose);
    	imageb.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	Button rt=(Button)RootActivity.this.findViewById(R.id.lose_return_button);
    	rt.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String bookno=tv1.getText().toString().trim();
					int flag=DBUtil.checktime(SnameID, bookno);	
					
					if(flag==0)
					{
						Toast.makeText
						(
								RootActivity.this,
								"本日借閱的書籍不能當日歸還", 
								Toast.LENGTH_SHORT
						).show();
					}
					else if(flag==1)
					{//正常書籍可以歸還
						String sql="delete from record where B_Num='"+bookno+"'";
						DBUtil.delete(sql);
						Toast.makeText
						(
								RootActivity.this,
								"歸還成功", 
								Toast.LENGTH_SHORT
						).show();
						gotoloseView();
					}
					else if(flag==-2)
					{
						String sql="delete from record where B_Num='"+bookno+"'";
						DBUtil.delete(sql);
						Toast.makeText
						(
								RootActivity.this,
								"已交過欠款，歸還成功", 
								Toast.LENGTH_SHORT
						).show();
						gotoloseView();
					}
					else if(flag==-1)
					{
						DBUtil.feeflag=false;
						String sql="delete from record where B_Num='"+bookno+"'";
						DBUtil.delete(sql);
						Toast.makeText
						(
								RootActivity.this,
								"您已存在欠費記錄，請不要忘記補交欠費", 
								Toast.LENGTH_SHORT
						).show();
						gotoloseView();
					}
				}
    				
    		}
    		);
    	
    	curr=WhichView.LOSE_DETAILS_VIEW;
    	
    }
       
    public void gotoyuyueView()
    {
    	setContentView(R.layout.yuyue); 
    	final EditText YYSH=(EditText)RootActivity.this.findViewById(R.id.yuyueEditSH);
    	final Button select=(Button)RootActivity.this.findViewById(R.id.yuyuequery01);
    	//imagebutton後退按鈕的監聽
    	ImageButton ibyuyuemain=(ImageButton)findViewById(R.id.ImageButtonyuyue);
    	ibyuyuemain.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	select.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						yuyuetemp=YYSH.getText().toString().trim();
					    temp7=DBUtil.selectbookinformationfrombookno(yuyuetemp);
						gotoyuyuedetailde();
					}
    				
    			}
    			);
    	curr=WhichView.YUYUE_VIEW;
    }
    public void  gotoyuyuedetailde()
    {
    	setContentView(R.layout.yuyuedetailed); 
    	TextView bookname=(TextView)RootActivity.this.findViewById(R.id.yuyuebooknametextview);
    	TextView yuyueauthor=(TextView)RootActivity.this.findViewById(R.id.yuyuebookauthortextview);
    	TextView yuyuebookpress=(TextView)RootActivity.this.findViewById(R.id.yuyuebookpresstextview);
    	TextView yuyueborrow=(TextView)RootActivity.this.findViewById(R.id.yuyuebookborrowtextview);
    	TextView yuyueorder=(TextView)RootActivity.this.findViewById(R.id.yuyuebookordertextview);
        final EditText yuyuestuno=(EditText)RootActivity.this.findViewById(R.id.yuyuestunumber);
    	final Button borrow=(Button)RootActivity.this.findViewById(R.id.borrowbook);
    	final Button order=(Button)RootActivity.this.findViewById(R.id.orderbook);
    	bookname.setText(temp7[1]);
    	yuyueauthor.setText(temp7[2]);
    	yuyuebookpress.setText(temp7[3]);
    	yuyueborrow.setText(temp7[4]);
    	yuyueorder.setText(temp7[5]);
    	ImageButton imagelose=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonyuyuedetails);
    	imagelose.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	borrow.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String StuNO=yuyuestuno.getText().toString().trim();
						String orderStuNO=DBUtil.getstu(yuyuetemp);
						int a=DBUtil.selectcount(StuNO);
						if(StuNO.equals(""))
						{
							Toast.makeText
    						(
    								RootActivity.this,
    								"學號不能為空", 
    								Toast.LENGTH_SHORT
    						).show();
						}
						else if(a>=borrowbook)
						{
							Toast.makeText
    						(
    								RootActivity.this,
    								"已超過借閱上限，不能借閱", 
    								Toast.LENGTH_SHORT
    						).show();
						}
						else if(temp7[4].equals("是"))
						{
							Toast.makeText
    						(
    								RootActivity.this,
    								"此書已經借閱，不能借閱", 
    								Toast.LENGTH_SHORT
    						).show();
							
						}
						else if(temp7[4].equals("否")&&temp7[5].equals("是")&&!StuNO.equals(orderStuNO))
						{
							Toast.makeText
    						(
    								RootActivity.this,
    								"此書已經被預約，不能預約", 
    								Toast.LENGTH_SHORT
    						).show();
						}
						else
						{
							DBUtil.borrowbook(temp[1],StuNO);
							Toast.makeText
    						(
    								RootActivity.this,
    								"借閱成功", 
    								Toast.LENGTH_SHORT
    						).show();
							gotoyuyueView();
						}
						
					
					}
    				
    			}
    			);
    	order.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String StuNO=yuyuestuno.getText().toString().trim();
						if(temp7[5].equals("否"))
						{
							DBUtil.orderbook(temp[1],StuNO);
							Toast.makeText
    						(
    								RootActivity.this,
    								"預約成功", 
    								Toast.LENGTH_SHORT
    						).show();
							gotoyuyueView();
						}
						
					}
    				
    			}
    			);
    	curr=WhichView.YUYUEDETAILED_VIEW;
    }
    
    public void gotostumainView()
    {
    	setContentView(R.layout.studentmanagementmain); 
    	final RadioButton selectstu=(RadioButton)findViewById(R.id.radiobuttonstudentmanagement_selectstu);
        final RadioButton addstu=(RadioButton)findViewById(R.id.radiobuttonstudentmanagerment_insertstu);
        final EditText stuNoEdit=(EditText)findViewById(R.id.studentmanagementnumberedit);
        final EditText stuNameEdit=(EditText)findViewById(R.id.studentmanagementnameedit);
        final EditText stuSexEdit=(EditText)findViewById(R.id.studentmanagementxingbieedit);
        final EditText stuQuanxianEdit=(EditText)findViewById(R.id.studentmanagementquanxianedit);
        final EditText stuAgeEdit=(EditText)findViewById(R.id.studentmanagementageedit);
        final EditText stuTelEdit=(EditText)findViewById(R.id.studentmanagementteledit);
        final EditText stuClassEdit=(EditText)findViewById(R.id.studentmanagementclassedit);
        final EditText stuXueyuanEdit=(EditText)findViewById(R.id.studentmanagementxueyuanedit);
        final EditText stuPwdEdit=(EditText)findViewById(R.id.studentmanagementpasswordedit);
        final LinearLayout selectlinearlayout=(LinearLayout)findViewById(R.id.studentmanagement_selecttulinearlayout);
        final LinearLayout addlinearlayout=(LinearLayout)findViewById(R.id.bookmanagement_addbooklinearlayout);
        final Button add=(Button)this.findViewById(R.id.buttonstudentmanagement_insertstu);
        final Button select=(Button)this.findViewById(R.id.buttonstudentmanagement_selectstu);
        final EditText stusnoEdit=(EditText)findViewById(R.id. studentmanagementnumberedit_select);   
      //imagebutton後退按鈕的監聽
    	ImageButton ibstumanagementmain=(ImageButton)findViewById(R.id.ImageButtonstudentmanagement);
    	ibstumanagementmain.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
        selectstu.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					selectlinearlayout.setVisibility(1);					   
    					addlinearlayout.setVisibility(-1);
    										
    				}
            		
            	}
            );
        addstu.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub    					
    					selectlinearlayout.setVisibility(-1);					   
    					addlinearlayout.setVisibility(1);  					
    				}
            		
            	}
            );
        add.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub    					
    					temp2[0]=stuNoEdit.getText().toString().trim();
    					temp2[1]=stuNameEdit.getText().toString().trim();
    					temp2[2]=stuSexEdit.getText().toString().trim();
    					temp2[3]=stuQuanxianEdit.getText().toString().trim();
    					temp2[4]=stuAgeEdit.getText().toString().trim();
    					temp2[5]=stuTelEdit.getText().toString().trim();   					
    					temp2[6]=stuClassEdit.getText().toString().trim();
    					temp2[7]=stuXueyuanEdit.getText().toString().trim();
    					temp2[8]=stuPwdEdit.getText().toString().trim();
    					if(temp2[0].equals("")||temp2[1].equals("")||temp2[2].equals("")||temp2[3].equals("")
    							||temp2[4].equals("")||temp2[5].equals("")||temp2[6].equals("") ||temp2[7].equals("")||
    							temp2[8].equals("")
    					) 
    					{
    						Toast.makeText
    						(
    								RootActivity.this,
    								"輸入的信息不能為空", 
    								Toast.LENGTH_SHORT
    						).show();
    					}
    					else
    					{
    						gotostuaddView();
    					}
    					
    				}
            		
            	}
            );
        select.setOnClickListener(
        		new OnClickListener()
        		{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String Stuno=stusnoEdit.getText().toString().trim();
						tempp=Stuno;
						temp3=DBUtil.selectStu(tempp);
						gotostuselectView();
					}
        			
        		}
        		);
        
    	curr=WhichView.STUMAIN_VIEW;
    }
    public void gotostuaddView()
    {
    	setContentView(R.layout.studentmanagementadd); 
    	TextView tNo=(TextView)RootActivity.this.findViewById(R.id.stuNotextview);
    	TextView tName=(TextView)RootActivity.this.findViewById(R.id.stunametextview);
    	TextView tSex=(TextView)RootActivity.this.findViewById(R.id.stusextextview);
    	TextView tQuanxian=(TextView)RootActivity.this.findViewById(R.id.stuquanxiantextview);
    	TextView tAge=(TextView)RootActivity.this.findViewById(R.id.stuagetextview);
    	TextView tTel=(TextView)RootActivity.this.findViewById(R.id.stuteltextview);
    	TextView tClass=(TextView)RootActivity.this.findViewById(R.id.stuclasstextview);
    	TextView tXueyuan=(TextView)RootActivity.this.findViewById(R.id.stuxueyuantextview);
    	TextView tPwd=(TextView)RootActivity.this.findViewById(R.id.stupwdtextview);
    	final Button bok=(Button)this.findViewById(R.id.addstudentbok);
    	//imagebutton後退按鈕的監聽
    	ImageButton ibstumanagementadd=(ImageButton)findViewById(R.id.ImageButtonstudentmanagement_add);
    	ibstumanagementadd.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	tNo.setText(temp2[0]);
    	tName.setText(temp2[1]);
    	tSex.setText(temp2[2]);
    	tQuanxian.setText(temp2[3]);
    	tAge.setText(temp2[4]);
    	tTel.setText(temp2[5]);
    	tClass.setText(temp2[6]);
    	tXueyuan.setText(temp2[7]);
    	tPwd.setText(temp2[8]);
    	 bok.setOnClickListener(
    			 new OnClickListener()
    			 {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						DBUtil.addStu(temp2[0],temp2[1],temp2[4],temp2[2],temp2[6],temp2[7],temp2[5],temp2[3],temp2[8]);
						Toast.makeText
						(
								RootActivity.this,
								"添加學生成功", 
								Toast.LENGTH_SHORT
						).show();
						gotostumainView();
					}
    				 
    			 }
    			 );
    	 
    	curr=WhichView.STUADD_VIEW;
    }
    public void gotostuselectView()
    {
    	setContentView(R.layout.studentmanagementselect); 
    	TextView tNo=(TextView)RootActivity.this.findViewById(R.id.select_stuNotextview);
    	TextView tName=(TextView)RootActivity.this.findViewById(R.id.select_stunametextview);
    	TextView tSex=(TextView)RootActivity.this.findViewById(R.id.select_stusextextview);
    	TextView tQuanxian=(TextView)RootActivity.this.findViewById(R.id.select_stuquanxiantextview);
    	TextView tAge=(TextView)RootActivity.this.findViewById(R.id.select_stuagetextview);
    	TextView tTel=(TextView)RootActivity.this.findViewById(R.id.select_stuteltextview);
    	TextView tClass=(TextView)RootActivity.this.findViewById(R.id.select_stuclasstextview);
    	TextView tXueyuan=(TextView)RootActivity.this.findViewById(R.id.select_stuxueyuantextview);
    	TextView tPwd=(TextView)RootActivity.this.findViewById(R.id.select_stupwdtextview);
    	final Button delete=(Button)this.findViewById(R.id.delectstudentbok);
    	final Button modify=(Button)this.findViewById(R.id.modifystudentbok);
    	 //imagebutton後退按鈕的監聽
    	ImageButton ibstumanagementselect=(ImageButton)findViewById(R.id.ImageButtonstudentmanagement_select);
    	ibstumanagementselect.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	tNo.setText(tempp);
    	tName.setText(temp3[0]);
    	tSex.setText(temp3[2]);
    	tQuanxian.setText(temp3[6]);
    	tAge.setText(temp3[1]);
    	tTel.setText(temp3[5]);
    	tClass.setText(temp3[3]);
    	tXueyuan.setText(temp3[4]);
    	tPwd.setText(temp3[7]);
    	delete.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DBUtil.delectStu(tempp);
						Toast.makeText
						(
								RootActivity.this,
								"刪除成功", 
								Toast.LENGTH_SHORT
						).show();
						gotostumainView();
					}
    				
    			}
    			);
    	modify.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						gotostumofidyView();
					}
    				
    			}    			
    			);
    	curr=WhichView.STUSELECT_VIEW;
    }
    public void gotostumofidyView()
    {
    	setContentView(R.layout.studentmanagementmofidy); 
        EditText stuNoEdit=(EditText)findViewById(R.id.modify_studentmanagementnumberedit);
        EditText stuNameEdit=(EditText)findViewById(R.id.modify_studentmanagementnameedit);
        EditText stuSexEdit=(EditText)findViewById(R.id.modify_studentmanagementxingbieedit);
        EditText stuQuanxianEdit=(EditText)findViewById(R.id.modify_studentmanagementquanxianedit);
        EditText stuAgeEdit=(EditText)findViewById(R.id.modify_studentmanagementageedit);
        EditText stuTelEdit=(EditText)findViewById(R.id.modify_studentmanagementteledit);
        EditText stuClassEdit=(EditText)findViewById(R.id.modify_studentmanagementclassedit);
        EditText stuXueyuanEdit=(EditText)findViewById(R.id.modify_studentmanagementxueyuanedit);
        EditText stuPwdEdit=(EditText)findViewById(R.id.modify_studentmanagementpasswordedit);
        final Button bok=(Button)this.findViewById(R.id.buttonstudentmanagement_modifystu);
        //imagebutton後退按鈕的監聽
    	ImageButton ibstumanagementmodify=(ImageButton)findViewById(R.id.ImageButtonstudentmanagement_modify);
    	ibstumanagementmodify.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
        stuNoEdit.setText(tempp);
        stuNameEdit.setText(temp3[0]);
        stuSexEdit.setText(temp3[2]);
        stuQuanxianEdit.setText(temp3[6]);
        stuAgeEdit.setText(temp3[1]);
        stuTelEdit.setText(temp3[5]);
        stuClassEdit.setText(temp3[3]);
        stuXueyuanEdit.setText(temp3[4]);
        stuPwdEdit.setText(temp3[7]);
        bok.setOnClickListener(
        		new OnClickListener()
        		{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DBUtil.updateStu(tempp, temp3[0], temp3[1], temp3[2], temp3[3],temp3[4], temp3[5], temp3[6], temp3[7]);
						Toast.makeText
						(
								RootActivity.this,
								"修改成功", 
								Toast.LENGTH_SHORT
						).show();
						gotostumainView();
					}
        			
        		}
        		);
    	curr=WhichView.STUMOFIDY_VIEW;
    }
    public void gotoandminmainView()
    {
    	setContentView(R.layout.adminmanagementmain);
    	final EditText adminname=(EditText)findViewById(R.id.adminnameedit);
        Button add=(Button)this.findViewById(R.id.addadmin);
        Button delete=(Button)this.findViewById(R.id.deleteadmin);
    	final Button select=(Button)this.findViewById(R.id.selectadmin);
        LinearLayout ll=(LinearLayout)findViewById(R.id.adminmainLinearLayout0);
    	 //imagebutton後退按鈕的監聽
    	ImageButton ibadminmanagement=(ImageButton)findViewById(R.id.ImageButtonadminmanagement);
    	//int a=DBUtil.CheckPermitted(adminname);
    	if(permitted!=1)
    	{
   		 delete.setEnabled(true);
   		 add.setEnabled(true);
 
    	}
    	else if(permitted==0)
    	{
    		delete.setEnabled(false);
      		 add.setEnabled(false);
    	} 
    	ibadminmanagement.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	select.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String mgNo=adminname.getText().toString().trim();
						tempp1=mgNo;
						temp4=DBUtil.SelectAdmin(mgNo);
						gotoandminselectView();
					}
    				
    			}
    			);
    	add.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						gotoadminaddView();
					}
    				
    			}
    			);
    	delete.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String mgNo=adminname.getText().toString().trim();
						DBUtil.deleteManager(mgNo);
						Toast.makeText
						(
								RootActivity.this,
								"刪除成功", 
								Toast.LENGTH_SHORT
						).show();
						gotoandminmainView();
					}
    				
    			}
    			);
    	curr=WhichView.ADMINMAIN_VIEW;
    }
    public void gotoadminaddView()
    {
    	setContentView(R.layout.adminmanagement_add);
    	final EditText adminname=(EditText)findViewById(R.id.adminnameedit_add);
    	final EditText adminpermitted=(EditText)findViewById(R.id.adminpremitededit_add);
    	final EditText adminpwd=(EditText)findViewById(R.id.adminpwdedit_add);
    	final Button addbok=(Button)this.findViewById(R.id.addadminbok);
    	 //imagebutton後退按鈕的監聽
    	ImageButton ibaddadmin=(ImageButton)findViewById(R.id.ImageButtonadminmanagement_add);
    	ibaddadmin.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	addbok.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String mgNo=adminname.getText().toString().trim();
						String mgpermitted=adminpermitted.getText().toString().trim();
						String mgpwd=adminpwd.getText().toString().trim();
						if(mgNo.equals("")||mgpermitted.equals("")||mgpwd.equals(""))
						{
							Toast.makeText
							(
									RootActivity.this,
									"請輸入信息", 
									Toast.LENGTH_SHORT
							).show();
						}
						else
						{
							if(DBUtil.insertManager(mgNo, mgpermitted, mgpwd))
							{
								Toast.makeText
								(
										RootActivity.this,
										"添加管理員成功", 
										Toast.LENGTH_SHORT
								).show();
								gotoandminmainView();
							}
						}
						
					}
    				
    			}
    			);
    	curr=WhichView.ADMINADD_VIEW;
    }
    public void gotoandminselectView()
    {
    	setContentView(R.layout.adminmanagement_selectadmin);
    	TextView mgNo=(TextView)RootActivity.this.findViewById(R.id.adminnametextview);
    	TextView permitted=(TextView)RootActivity.this.findViewById(R.id.adminpermittedtextview_select);
    	TextView pwd=(TextView)RootActivity.this.findViewById(R.id.adminpasswordtextview_select);
    	final Button modifypwd=(Button)this.findViewById(R.id.modifypwd);
    	 //imagebutton後退按鈕的監聽
    	ImageButton ibadminmanagementselect=(ImageButton)findViewById(R.id.ImageButtonadminmanagement_select);
    	ibadminmanagementselect.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	mgNo.setText(tempp1);
    	permitted.setText(temp4[0]);
    	pwd.setText(DBUtil.selectAdminPassword(tempp1));
    	modifypwd.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						gotoandminmofidyView();
					}
    				
    			}
    			);
    	curr=WhichView.ADMINSELECT_VIEW;
    	
    }
    public void gotoandminmofidyView()
    {
    	setContentView(R.layout.adminmanagement_mofidy);
    	TextView mgNo=(TextView)RootActivity.this.findViewById(R.id.adminnametextview_mofidy);
    	TextView mgpermited=(TextView)RootActivity.this.findViewById(R.id.adminpermittedtextview_mofidy);
    	final EditText adminoldpwd=(EditText)findViewById(R.id.adminoldpwdedit_mofidy);
    	final EditText adminnewpwd1=(EditText)findViewById(R.id.adminnewpwdedit_mofidy);
    	final EditText adminnewpwd2=(EditText)findViewById(R.id.adminnewpwd2edit_mofidy);
    	final Button bok=(Button)this.findViewById(R.id.modifypwdbok);
    	 //imagebutton後退按鈕的監聽
    	ImageButton ibadminmanagementmodify=(ImageButton)findViewById(R.id.ImageButtonadminmanagement_mofidy);
    	ibadminmanagementmodify.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	mgNo.setText(tempp1);
    	mgpermited.setText(temp4[0]);
    //	adminoldpwd.setText(temp4[1]);  temp4[1]中存入的是老密碼
       	bok.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					if(adminnewpwd1.getText().toString().trim().equals("")||adminnewpwd2.getText().toString().trim().equals(""))
    					{
    						Toast.makeText
    						(
    								RootActivity.this,
    								"請輸入新密碼和和確認新密碼", 
    								Toast.LENGTH_SHORT
    						).show();
    					}
    					else 
    					{
    						String oldpassword1=adminoldpwd.getText().toString().trim();
    						String oldpassword2=DBUtil.selectAdminPassword(tempp1);
    						if(oldpassword2.equals(oldpassword1))
    						{
    							DBUtil.updateManager(tempp1,oldpassword2);
    	    					Toast.makeText
    							(
    									RootActivity.this,
    									"修改密碼成功", 
    									Toast.LENGTH_SHORT
    							).show();
    	    					gotoandminmainView();
    						}
    						else
    						{
    							Toast.makeText
    							(
    									RootActivity.this,
    									"修改失敗,請確定新密碼和確認密碼一致", 
    									Toast.LENGTH_SHORT
    							).show();
    							
    						}
    					}
    					
    					
    				}
            		
            	}
            );
    	curr=WhichView.ADMINMOFIDY_VIEW;
    	
    }
  
    public void gotoFee()
    {
    	setContentView(R.layout.fee); 
    	final Button selectfee=(Button)this.findViewById(R.id.selectfee);
    	final EditText feestuNo=(EditText)this.findViewById(R.id.feestudentnumberedit);
    	ImageButton ibfee=(ImageButton)findViewById(R.id.ImageButtonfee1);
    	ibfee.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goToMainMenu();
    				}
            		
            	}
            );
    	selectfee.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String feeStuNO=feestuNo.getText().toString().trim();
						stunotemp=feeStuNO;
						if(feeStuNO.equals(""))
						{
							Toast.makeText
							(
									RootActivity.this,
									"學號不能為空", 
									Toast.LENGTH_SHORT
							).show();
						}else
						{
							feetodetailed=DBUtil.selectfeeinformation(feeStuNO);
							if(feetodetailed.size()==0)
							{
								Toast.makeText
								(
										RootActivity.this,
										"沒有查到與你輸入相關的資料！", 
										Toast.LENGTH_SHORT
								).show();								
							 }
							else
							{
								
								gotobookfeedetailedfrid();
                                
							}
							
						}
						
					}
    				
    			}
    			);
    	curr=WhichView.FEE_VIEW;
    }
    public void gotobookfeedetailedfrid()
    {
    	setContentView(R.layout.fee_detailed_grid);
    	GridView gv=(GridView)findViewById(R.id.GridViewfee01);
    	generateDataListfee(feetodetailed);
		gv.setAdapter(gridViewfee(feetodetailed));
		ImageButton imagefee=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonfeedetailed);
    	imagefee.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
		//設置選項選中的監聽器
        gv.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//重寫選項被選中事件的處理方法
				
			}
            
			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        ); 
        //設置單擊監聽
        gv.setOnItemClickListener(
                new OnItemClickListener()
                {
     			@Override
     			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
     					long arg3) {//重寫選項被單擊事件的處理方法
     				LinearLayout ll=(LinearLayout)arg1;//獲取當前選中選項對應的LinearLayout
    				LinearLayout l2=(LinearLayout)ll.getChildAt(0);				
    				TextView tvn=(TextView)l2.getChildAt(1);//書號
    				LinearLayout l3=(LinearLayout)ll.getChildAt(1);
    				TextView tvn1=(TextView)l3.getChildAt(1);//書名
    				LinearLayout l4=(LinearLayout)ll.getChildAt(2);	
    				TextView tvn2=(TextView)l4.getChildAt(1);	//日期
    				temp6[0]=tvn.getText().toString().trim();   //書號
    				temp6[1]=tvn1.getText().toString().trim();   //書名
    				temp6[2]=tvn2.getText().toString().trim();   //天數
    			    gotofeexianshiView();
    			
    				
     			}        	   
                }
             );
       
    	ImageButton ibfeedatailed=(ImageButton)findViewById(R.id.ImageButtonfeedetailed);
    	ibfeedatailed.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goToMainMenu();
    				}
            		
            	}
            );
    	curr=WhichView.FEE_DRTILED;
    }
    public void  gotofeexianshiView()
    {
    	setContentView(R.layout.fee_xianshi);
    	TextView stuno=(TextView)RootActivity.this.findViewById(R.id.feebstunumber_xianshi02);
    	TextView bookno=(TextView)RootActivity.this.findViewById(R.id.feebooknumber_xianshi02);
    	TextView bookname=(TextView)RootActivity.this.findViewById(R.id.feebookname_xianshi02);
    	TextView dalaytime=(TextView)RootActivity.this.findViewById(R.id.feedelaytime_xianshi02);
    	TextView money=(TextView)RootActivity.this.findViewById(R.id.feejiaofei_xianshi02);
    	final Button bok=(Button)this.findViewById(R.id.feemoney);
    	 stuno.setText(stunotemp);
    	 bookno.setText(temp6[0]);
    	 bookname.setText(temp6[1]);
    	 dalaytime.setText(temp6[2]);
    	 money.setText(String.valueOf(0.1*Double.parseDouble(temp6[2])));
    	final String str = String.valueOf(0.1*Double.parseDouble(temp6[2])); 
    	ImageButton imagefee=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonfee2);
    	imagefee.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	 bok.setOnClickListener(
    			 new OnClickListener()
    			 {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub 
						  DBUtil.fee(stunotemp, str);
						   Toast.makeText
							(
									RootActivity.this,
									"交費成功", 
									Toast.LENGTH_SHORT
							).show();
						   gotoFee();
					}
    				 
    			 }
    			 );
    	
    	curr=WhichView.FEE_XIANSHI;
    }
    
    public void gotoDengLuView()
    {
    	setContentView(R.layout.main);   
    	final Button dlu=(Button)this.findViewById(R.id.denglu);
    	final Button chz=(Button)this.findViewById(R.id.reset);
    	final EditText yhm=(EditText)findViewById(R.id.yhm);
    	final EditText pwd=(EditText)findViewById(R.id.pwd);
    	
    	
    	dlu.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String sname=yhm.getText().toString().trim();
					String spwd=pwd.getText().toString().trim();
					String ppwd=DBUtil.selectADPwd(sname);
					
					if(spwd.equals(ppwd))
					{
						permitted=DBUtil.CheckPermitted(sname);	
						goToMainMenu();
						
					}
					else 
					{
						Toast.makeText
						(
								RootActivity.this,
								"登陸失敗", 
								Toast.LENGTH_SHORT
						).show();
					}
				}
    			
    		}
    	);

        chz.setOnClickListener
        (
    		new  OnClickListener()
    		{
				@Override
				public void onClick(View v) 
				{
					//goToMainMenu();					
					yhm.setText("");
					pwd.setText("");
				}    			
    		}
        );
    	
    	curr=WhichView.DENGLU_VIEW;
    }
    
    public void gotobookmanagementmainView() 
    {
    	setContentView(R.layout.bookmanagementmain);
    	 final RadioButton selectbook=(RadioButton)this.findViewById(R.id.radiobuttonbookmanagement_selectbook);
    	 final RadioButton insertbook=(RadioButton)this.findViewById(R.id.radiobuttonbookmanagerment_insertbook);
    	 final LinearLayout selectlinearlayout=(LinearLayout)findViewById(R.id.bookmanagement_selectbooklinearlayout);
    	 final LinearLayout insertlinearlayout=(LinearLayout)findViewById(R.id.bookmanagement_insertbooklinearlayout);
    	 final EditText bookmanagementisbnEdit=(EditText)findViewById(R.id.bookmanagementisbnedit);
    	 final Button bselectbook=(Button)this.findViewById(R.id.buttonbookmanagement_selectbook);
    	//imagebutton後退按鈕的監聽
    	ImageButton ibbookmanagmentmain=(ImageButton)findViewById(R.id.ImageButtonbookmanagement01);
    	isbnnumber=bookmanagementisbnEdit.getText().toString().trim();
    	ibbookmanagmentmain.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );
    	//查詢圖書和圖書入庫兩個單選按鈕的同步的方法 
    	selectbook.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					
    					selectlinearlayout.setVisibility(1);					   
    					insertlinearlayout.setVisibility(-1);
    				}
            		
            	}
            );
    	insertbook.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub    					    						
    					gotobookmanagemanetView();
    				}
            		
            	}
            );
    	
    	bselectbook.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String isbn=bookmanagementisbnEdit.getText().toString().trim();
						
						if(isbn.equals(""))
						{
							Toast.makeText
							(
									RootActivity.this,
									"請輸入要查詢圖書的的ISBN號", 
									Toast.LENGTH_SHORT
							).show();
						}
						else if(isbn.length()!=13)
						{
							Toast.makeText
							(
									RootActivity.this,
									"請按正確格式輸入13位ISBN號,例如9787302164289", 
									Toast.LENGTH_SHORT
							).show();
						}
						else
						{
							temp1=isbn;
							selectTOgird=DBUtil.selectbookfromISBN(isbn);
							if(selectTOgird.size()==0)
							{
								Toast.makeText
								(
										RootActivity.this,
										"沒有查到與你輸入相關的資料！", 
										Toast.LENGTH_SHORT
								).show();								
							 }
							else
							{                                
                                
							}
							final Vector<String> vvv=new Vector<String>();
							for(int i=0;i<selectTOgird.size();i++)
							{
								vvv.add(selectTOgird.get(i));
							}
							gotobookmanagementselectbook();
						}
						
					}
    				
    			}
    			);   			
    	curr=WhichView.BOOKMAIN_VIEW;
    }
    public void gotobookmanagementselectbook()
    { 
    	setContentView(R.layout.bookmanagement_selectbook);
    	TextView t1=(TextView)RootActivity.this.findViewById(R.id.bookmanagementselect_bookisbnTextView01);//isbn
    	TextView t2=(TextView)RootActivity.this.findViewById(R.id.bookmanagementselect_buytimeTextView02); //buytime
    	TextView t3=(TextView)RootActivity.this.findViewById(R.id.bookmanagementselect_booknameTextView03);//bookname
    	TextView t4=(TextView)RootActivity.this.findViewById(R.id.bookmanagementselect_bookauthorTextView04);//author
    	TextView t5=(TextView)RootActivity.this.findViewById(R.id.bookmanagementselect_bookpressTextView05);//press
    	TextView t6=(TextView)RootActivity.this.findViewById(R.id.bookmanagementselect_bookintroductionTextView06);//簡介
    	GridView gv=(GridView)findViewById(R.id.GridView01);
    	 final Button bmodifybook=(Button)this.findViewById(R.id.mofidybookbok);
    	generateDataList(selectTOgird);
		gv.setAdapter(gridView(selectTOgird));
		t1.setText(temp1);
		t2.setText(selectTOgird.get(3).toString());
		t3.setText(selectTOgird.get(0).toString());
		t4.setText(selectTOgird.get(1).toString());
		t5.setText(selectTOgird.get(2).toString());
		t6.setText(selectTOgird.get(7).toString());
    		//imagebutton後退按鈕的監聽
     	ImageButton ibbookmanagmentmain=(ImageButton)findViewById(R.id.ImageButtonbookmanagement03);
     	ibbookmanagmentmain.setOnClickListener(
             	new OnClickListener()
             	{

     				@Override
     				public void onClick(View v) {
     					// TODO Auto-generated method stub
     					goback();
     				}
             		
             	}
             );
		//設置選項選中的監聽器
        gv.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//重寫選項被選中事件的處理方法
				
			}
            
			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        ); 
        //設置單擊監聽
        gv.setOnItemClickListener(
                new OnItemClickListener()
                {
     			@Override
     			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
     					long arg3) {//重寫選項被單擊事件的處理方法
     				LinearLayout ll=(LinearLayout)arg1;//獲取當前選中選項對應的LinearLayout
    				LinearLayout l2=(LinearLayout)ll.getChildAt(0);				
    				TextView tvn=(TextView)l2.getChildAt(0);//書號
    				TextView tvn1=(TextView)l2.getChildAt(1);//已借閱
    				TextView tvn2=(TextView)l2.getChildAt(2);//已預約
    				temp5[0]=tvn.getText().toString().trim();
    				temp5[1]=tvn1.getText().toString().trim();
    				temp5[2]=tvn2.getText().toString().trim();
    			
    			
    				
     			}        	   
                }
             );
        bmodifybook.setOnClickListener(
             	new OnClickListener()
             	{

     				@Override
     				public void onClick(View v) {
     					// TODO Auto-generated method stub
     					gotobookmanagementmodifybookView();
     				}
             		
             	}
             );
    	curr=WhichView.BOOKMANAGEMENT_SELECT;
    }
   	public void gotobookmanagemanetView()
    {
    	setContentView(R.layout.bookmanagement);
    	final Spinner jieyuesp=(Spinner)findViewById(R.id.jieyueSpinner01);
    	final Spinner yuyuesp=(Spinner)findViewById(R.id.yuyueSpinner01);
    	List<CItem > lst = new ArrayList<CItem>();
        CItem  ct = new CItem ("1","是");
        CItem  ct1 = new CItem ("2","否");
        lst.add(ct);
        lst.add(ct1);   
        ArrayAdapter<CItem > Adapter = new ArrayAdapter<CItem>(RootActivity.this,
            android.R.layout.simple_spinner_item, lst);
          jieyuesp.setAdapter(Adapter);
          yuyuesp.setAdapter(Adapter);
          final Button bok=(Button)this.findViewById(R.id.insertbookbok);
          final EditText isbnEdit=(EditText)findViewById(R.id.bookisbnedit);
          final EditText authorEdit=(EditText)findViewById(R.id.bookauthoredit);
          final EditText nameEdit=(EditText)findViewById(R.id.booknameedit);
          final EditText pressEdit=(EditText)findViewById(R.id.bookpressedit);
          final EditText instroductionEdit=(EditText)findViewById(R.id.bookinstroductionedit);	
          final  EditText  dateEdit01=(EditText )findViewById(R.id.bookbuttimeedit); //日期輸入框
  		  dateEdit01.setText(DateUtil.getSystemDateTime());
      
  		ImageButton imagefee=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonbookmanagement02);
    	imagefee.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
  		  dateEdit01.setOnClickListener(
  			new OnClickListener()
  			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//按下此按鈕顯示時間輸入對話框
					showDialog(DATE_INPUT_DIALOG_ID);
					//年
					EditText tempyear=(EditText)dateInputdialog.findViewById(R.id.EditText01);
					//月
					EditText tempmonth=(EditText)dateInputdialog.findViewById(R.id.EditText02);
					//日
					EditText tempdate=(EditText)dateInputdialog.findViewById(R.id.EditText03);
					
					String[] str=DateUtil.getSystemDateTime().split("\\-"); //分割系統時間
					tempyear.setText(str[0]);   //設置文本為系統時間
					tempmonth.setText(str[1]);
					tempdate.setText(str[2]);
					
				}
  				
  				
  			}
  		);
          
    	bok.setOnClickListener(
    			new OnClickListener()
    			{
              	@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						temp[0]=isbnEdit.getText().toString().trim();
						temp[1]=authorEdit.getText().toString().trim();
						temp[3]=nameEdit.getText().toString().trim();
						temp[4]=pressEdit.getText().toString().trim();
						temp[7]=instroductionEdit.getText().toString().trim();
						temp[8]=DBUtil.getBookNumber();
					    datetemp=dateEdit01.getText().toString().trim();
						String id1=((CItem)jieyuesp.getSelectedItem()).GetID().toString().trim();
				    	String id2=((CItem)yuyuesp.getSelectedItem()).GetID().toString().trim();
						if(id1.equals("1"))
						{
							temp[5]="是";
						}
						else
						{
							temp[5]="否";
						}
						if(id2.equals("1"))
						{
							temp[6]="是";
						}
						else
						{
							temp[6]="否";
						}
						gotobookmentinsertbookxianshiView(); 
					}
    				
    			}
    			);
    	curr=WhichView.BOOKMANAGEMENT_INSERTVIEW;
    }
    public void gotobookmanagementmodifybookView()
    {
    	setContentView(R.layout.bookmanagement_modifybook_xianshi);
    	TextView isbn=(TextView)RootActivity.this.findViewById(R.id.modifybookisbnedit);
    	TextView buytime=(TextView)RootActivity.this.findViewById(R.id.modifybookbuttimeedit);
    	TextView bookname=(TextView)RootActivity.this.findViewById(R.id.modifybooknameedit);
    	TextView bookauthor=(TextView)RootActivity.this.findViewById(R.id.modifybookauthoredit);
    	TextView press=(TextView)RootActivity.this.findViewById(R.id.modifybookpressedit);   	
    	TextView instroduction=(TextView)RootActivity.this.findViewById(R.id.modifybookinstroductionedit);
    	final Button bok=(Button)this.findViewById(R.id.modifybook);
    	final Button bdlt=(Button)this.findViewById(R.id.deletebook);
    	ImageButton ibmodifybook=(ImageButton)findViewById(R.id.ImageButtonbookmanagement11);
    	isbn.setText(temp1);
    	buytime.setText(selectTOgird.get(3).toString());
    	bookname.setText(selectTOgird.get(0).toString());
    	bookauthor.setText(selectTOgird.get(1).toString());
    	press.setText(selectTOgird.get(2).toString());
    	instroduction.setText(selectTOgird.get(7).toString());
    	bok.setOnClickListener(
    			
    	    	new OnClickListener()
    	    {

    		@Override
    			public void onClick(View v) {
    			// TODO Auto-generated method stub
    			DBUtil.updateBook(temp5[0],selectTOgird.get(0).toString(),selectTOgird.get(1).toString(),selectTOgird.get(2).toString(),selectTOgird.get(3).toString(),temp5[1],temp5[2],selectTOgird.get(7).toString());	
    			Toast.makeText
				(
						RootActivity.this,
						"修改成功", 
						Toast.LENGTH_SHORT
				).show();	
    			gotobookmanagementselectbook();
    		  }
    	    				
    	   }
	);
    	bdlt.setOnClickListener(
    			new OnClickListener()
    			{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DBUtil.deleteBook(temp5[0]);
    					Toast.makeText
    					(
    							RootActivity.this,
    							"信息已刪除", 
    							Toast.LENGTH_SHORT
    					).show();
    					gotobookmanagementselectbook();
					}
    				
    			}
    			);
    	ibmodifybook.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					goback();
    				}
            		
            	}
            );


    	curr=WhichView.BOOKMANAGEMENT_MODIFY;
    }
    public void gotobookmentinsertbookxianshiView()
    {
   setContentView(R.layout.bookmanagement_insertbook_xianshi); 
   TextView tisbn=(TextView)RootActivity.this.findViewById(R.id.insertbookisbntextview);
   TextView tbuytime=(TextView)RootActivity.this.findViewById(R.id.bookbuytimetextview);
   TextView tname=(TextView)RootActivity.this.findViewById(R.id.booknametextview);
   TextView tauthor=(TextView)RootActivity.this.findViewById(R.id.bookauthortextview);
   TextView tpress=(TextView)RootActivity.this.findViewById(R.id.bookpresstextview);
   TextView tjieyue=(TextView)RootActivity.this.findViewById(R.id.bookyijieyuetextview);
   TextView tyuyue=(TextView)RootActivity.this.findViewById(R.id.bookyiyuyuetextview);
   TextView tinstroduction=(TextView)RootActivity.this.findViewById(R.id.bookinstroductiontextview);
   TextView tbooknumber=(TextView)RootActivity.this.findViewById(R.id.booknumbertextview);
   final Button bok=(Button)this.findViewById(R.id.insertxianshibookbok);
   final Button bce=(Button)this.findViewById(R.id.backxianshibookbok);
   tisbn.setText(temp[0]);	  
   tbuytime.setText(datetemp);
   tname.setText(temp[3]);
   tauthor.setText(temp[1]);
   tpress.setText(temp[4]);
   tinstroduction.setText(temp[7]);
   tjieyue.setText(temp[5]);
   tyuyue.setText(temp[6]);
   tbooknumber.setText(temp[8]);
	ImageButton imagefee=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonbookmanagement04);
	imagefee.setOnClickListener(
		new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goback();
			}
			
		}
	);
        bok.setOnClickListener(
        		new OnClickListener()
        		{

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						DBUtil.insertBook(temp[0],temp[8],temp[3],temp[1],temp[4],temp[2],temp[5],temp[6],temp[7]);
						Toast.makeText
						(
								RootActivity.this,
								"圖書入庫成功", 
								Toast.LENGTH_SHORT
						).show();
						gotobookmanagementmainView();
					}
        			
        		}
        		);
        bce.setOnClickListener(
        		new OnClickListener()
        		{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						goback();
					}
        			
        		}
        		);
   
   
    	curr=WhichView.BOOKMANAGEMENT_INSERTVIEW_XIANSHI;
    }
    //幫助界面
    public void goToHelpView()
    {
    	setContentView(R.layout.helpview);
    	curr=WhichView.HELP_VIEW;
    }
     
    //關於界面
    public void goToAboutView()
    {
    	setContentView(R.layout.aboutview);
    	curr=WhichView.ABOUT_VIEW;
    }
    
    
    public boolean goback()
    {
    	if(curr==WhichView.BOOKMAIN_VIEW)
		{
    		goToMainMenu();
			return true;
		}
    	if(curr==WhichView.BOOKMANAGEMENT_INSERTVIEW)
    	{
    		gotobookmanagementmainView();
    		return true;
    	}
    	if(curr==WhichView.BOOKMANAGEMENT_INSERTVIEW_XIANSHI)
    	{
    		gotobookmanagemanetView(); 
    		return true;
    	}
    	if(curr==WhichView.STUSELECT_VIEW)
    	{
    		gotostumainView();
    		return true;
    	}
    	if(curr==WhichView.STUADD_VIEW)
    	{
    		gotostumainView();
    		return true;
    	}
    	if(curr==WhichView.STUMOFIDY_VIEW)
    	{
    		gotostuselectView();
    		return true;
    	}
    	if(curr==WhichView.BOOKMAIN_VIEW)
    	{
    		goToMainMenu();
    		return true;
    	}
    	if(curr==WhichView.FEE_VIEW)
    	{
    		goToMainMenu();
    		return true;
    	}
    	if(curr==WhichView.MAIN_MENU)
    	{
    		gotoDengLuView();
    		return true;
    	}
    	if(curr==WhichView.STUMAIN_VIEW)
    	{
    		goToMainMenu();
    		return true;
    	}
    	if(curr==WhichView.ADMINMAIN_VIEW)
    	{
    		goToMainMenu();
    		return true;
    	}
    	if(curr==WhichView.ADMINSELECT_VIEW)
    	{
    		gotoandminmainView();
    		return true;
    	}
    	if(curr==WhichView.ADMINADD_VIEW)
    	{
    		gotoandminmainView();
    		return true;
    	}
    	if(curr==WhichView.ADMINMOFIDY_VIEW)
    	{
    		gotoandminselectView();
    		return true;
    	}
    	if(curr==WhichView.BOOKMANAGEMENT_INSERTVIEW)
    	{
    		gotobookmanagementmainView();
    		return true;
    	}
    	if(curr==WhichView.BOOKMANAGEMENT_MODIFY)
    	{
    		gotobookmanagementselectbook();
    		return true;
    	}
    	if(curr==WhichView.BOOKMANAGEMENT_SELECT)
    	{
    		gotobookmanagementmainView();  
    		return true;
    	}
    	if(curr==WhichView.LOSE_VIEW)
		{
			goToMainMenu();	
			return true;
		}
		if(curr==WhichView.LOSE_INFO_VIEW)
		{
			gotoloseView();
			return true;
		}
		if(curr==WhichView.LOSE_DETAILS_VIEW)
		{
			gotoloseinfoView();
			return true;
		}
		if(curr==WhichView.QUERY_VIEW)
		{
    		gotoQueryMainView();	
			return true;
		}
		if(curr==WhichView.GIRD_VIEW)
		{
			gotoQueryView();
			return true;
		}
		if(curr==WhichView.DETIALSVIEW)
		{
			gotogirdView();
			return true;
		}
		if(curr==WhichView.QUERYMAIN_VIEW)
		{
			goToMainMenu();	
			return true;
		}
		if(curr==WhichView.SELF_VIEW_INFO)
		{
			gotoQueryMainView();	
			return true;
		}
		if(curr==WhichView.FEE_DRTILED)
		{
			gotoFee();
			return true;
		}
		if(curr==WhichView.FEE_XIANSHI)
		{
			gotobookfeedetailedfrid();
			return true;
		}
		if(curr==WhichView.YUYUEDETAILED_VIEW)
		{
			gotoyuyueView();
			return true;
		}
		if(curr==WhichView.YUYUE_VIEW)
		{
			goToMainMenu();	
			return true;
		}
		if(curr==WhichView.HELP_VIEW)
		{
			goToMainMenu();	
			return true;
		}
		if(curr==WhichView.ABOUT_VIEW)
		{
			goToMainMenu();	
			return true;
		}
    	return false;
    }
    
  //等到表格的行數等得到的表格的基本信息
    public List<? extends Map<String, ?>> generateDataList(Vector<String> v)
    		{
    	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
    	    	int rowCounter=v.size()/8;//得到表格的行數
    	    	for(int i=0;i<rowCounter;i++)
    	    	{//循環生成每行的包含對應各個列數據的Map；col1、col2、col3為列名
    	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
//    	    		hmap.put("col1", v.get(i*8+0));   //第一列為	書名
//    	    		hmap.put("col2", v.get(i*8+1));//第二例作者
//    	    		hmap.put("col3", v.get(i*8+2));//第三列出版社
//    	    		hmap.put("col4", v.get(i*8+3));//第四列為購買時間
    	    		hmap.put("col5", v.get(i*8+4));//第五列為書號
    	    		hmap.put("col6", v.get(i*8+5));//第六列為借閱
    	    		hmap.put("col7", v.get(i*8+6));//第七列為預約
//    	    		hmap.put("col8", v.get(i*8+7));//第八列為介紹
    	    		list.add(hmap);
    	    	}    	
    	    	return list;
    		}
    //fee
    public List<? extends Map<String, ?>> generateDataListfee(Vector<String> v)
    		{
    	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
    	    	int rowCounter=v.size()/3;//得到表格的行數
    	    	for(int i=0;i<rowCounter;i++)
    	    	{//循環生成每行的包含對應各個列數據的Map；col1、col2、col3為列名
    	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
    	    		hmap.put("col1", v.get(i*3+0));   //第一列為	書號
    	    		hmap.put("col2", v.get(i*3+1));//第二例書名
    	    		hmap.put("col3", v.get(i*3+2));//第三列天數
    	    		list.add(hmap);
    	    	}    	
    	    	return list;
    		}
    public BaseAdapter gridView(Vector<String> v)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
        		this,
                generateDataList(v), //數據List
                R.layout.grid_row, //行對應layout id
                new String[]{"col5","col6","col7"}, //列名列表
                new int[]{R.id.booknumberTextView02,R.id.jieyueTextView,R.id.yuyueTextView10,}//列對應控件id列表
        		
        );
    	return sca;
        	
    }
    
   //fee
    public BaseAdapter gridViewfee(Vector<String> v)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
        		this,
                generateDataListfee(v), //數據List
                R.layout.fee_grid_row, //行對應layout id
                new String[]{"col1","col2","col3"}, //列名列表
                new int[]{R.id.booknumberTV02,R.id.booknameTV02,R.id.delaytiemTV02}//列對應控件id列表
        		
        );
    	return sca;
        	
    }
    //掛失歸還
    public List<? extends Map<String, ?>> generateDataList2(Vector<String> v)
     		{
     	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
     	    	int rowCounter=v.size()/7;//得到表格的行數
     	    	for(int i=0;i<rowCounter;i++)
     	    	{//循環生成每行的包含對應各個列數據的Map；col1、col2、col3為列名
     	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
     	    		hmap.put("col1", v.get(i*7+0));   //第一列為書號		
     	    		hmap.put("col2", v.get(i*7+3));//第二例為ISBN
     	    		hmap.put("col3", v.get(i*7+4));//第三列為書名
     	    		hmap.put("col4", v.get(i*7+5));//第四列為作者
     	    		hmap.put("col5", v.get(i*7+6));//第五列為出版社     	    		
     	    		list.add(hmap);
     	    	}    	
     	    	return list;
     		}
  //圖書查詢主界面
    public void gotoQueryMainView()
    {
    	setContentView(R.layout.self_or_query);
    	//後退按鈕的設置監聽
    	ImageButton selfib=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonself_query);
    	selfib.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	
    	//查詢圖書的界面
    	Button selfb=(Button)RootActivity.this.findViewById(R.id.self_or_bok);
    	selfb.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					gotoQueryView();
				}
    			
    		}
    	);  	
    	curr=WhichView.QUERYMAIN_VIEW;
    }
    
//    }
public void gotoQueryView()
    {
    	setContentView(R.layout.query);
    	final Spinner sp=(Spinner)findViewById(R.id.Spinner01);
    	List<CItem > lst = new ArrayList<CItem>();
        CItem  ct = new CItem ("1","書名");
        CItem  ct1 = new CItem ("2","作者");
        CItem  ct2 = new CItem ("3","出版社");
        lst.add(ct);
        lst.add(ct1);
        lst.add(ct2);
        ArrayAdapter<CItem > Adapter = new ArrayAdapter<CItem>(RootActivity.this,
            android.R.layout.simple_spinner_item, lst);
        sp.setAdapter(Adapter);
        
        Button sbmit=(Button)findViewById(R.id.querybok);
        final RadioButton simpleq=(RadioButton)findViewById(R.id.simpleQuery);
        final RadioButton highq=(RadioButton)findViewById(R.id.highQuery);
        
        final EditText simpleEdit=(EditText)findViewById(R.id.simpleQueryEdit);
        final EditText highEditSM=(EditText)findViewById(R.id.highEditSM);
        final EditText highEditZZ=(EditText)findViewById(R.id.highEditZZ);
        final EditText highEditCBS=(EditText)findViewById(R.id.highEditCBS);
        
        final LinearLayout simple=(LinearLayout)findViewById(R.id.linearsimple);
        final LinearLayout high=(LinearLayout)findViewById(R.id.linearhigh);
                        
        //imagebutton後退按鈕的監聽
        ImageButton ibquery=(ImageButton)findViewById(R.id.ImageButtonquery);
        ibquery.setOnClickListener(
        	new OnClickListener()
        	{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
        		
        	}
        );
          
       //兩個簡單和高級單選按鈕的單擊的監聽的方法 
        simpleq.setOnClickListener(
        	new OnClickListener()
        	{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					   simple.setVisibility(1);					   
					   high.setVisibility(-1);
				}
        		
        	}
        );
        //高級單選按鈕的單擊監聽方法       
        highq.setOnClickListener(
            	new OnClickListener()
            	{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub    					    						
    					   simple.setVisibility(-1);
 					       high.setVisibility(1);    					
    				}
            		
            	}
            );
                      
        sbmit.setOnClickListener(
        	new OnClickListener()
        	{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Vector<String> vvvv =new Vector<String>();
					//簡單查詢的事件監聽
					if(simpleq.isChecked())
					{						
						String result=simpleEdit.getText().toString().trim();
						String id=((CItem)sp.getSelectedItem()).GetID().toString().trim();
						
						if(result.equals(""))
						{
							Toast.makeText
							(
									RootActivity.this,
									"輸入不能為空，請輸入要查詢的內容!", 
									Toast.LENGTH_SHORT
							).show();	
						}
					  
						else 
						{	
							resultnumdetails.clear();
						    if(id.equals("1"))//通過輸入書名進行查詢
						    {
						    	queryTOgird=DBUtil.selectAllfrombook(result);
						    	toast(queryTOgird,resultnumdetails);															
						     }
						    else if(id.equals("2"))//通過作者進行查詢
						    {
						    	queryTOgird=DBUtil.getAuthorAllfromBook(result);
						    	toast(queryTOgird,resultnumdetails);
						    }
						   else if(id.equals("3"))//通過出版社進行查詢
						    {
							    queryTOgird=DBUtil.getPubAllfrombook(result);
							    toast(queryTOgird,resultnumdetails);			
						    }
						}
					}
					else if(highq.isChecked())
					{
						String highSM=highEditSM.getText().toString().trim();
						String highZZ=highEditZZ.getText().toString().trim();
						String highCBS=highEditCBS.getText().toString().trim();
						resultnumdetails.clear();
						if(highSM.equals("")&&highZZ.equals("")&&highCBS.equals(""))
						{
							Toast.makeText
							(
									RootActivity.this,
									"輸入不能全為空，請輸入要查詢的內容!", 
									Toast.LENGTH_SHORT
							).show();	
						}
						//書名和作者的組合查詢
						else if((!highSM.equals(""))&&(!highZZ.equals(""))&&(highCBS.equals("")))							
						{
							queryTOgird=DBUtil.getBnAuAllfrombook(highSM, highZZ);
							toast(queryTOgird,resultnumdetails);
						}
						//書名和出版社的組合查詢
						else if((!highSM.equals(""))&&(highZZ.equals(""))&&(!highCBS.equals("")))
						{
							queryTOgird=DBUtil.getBnCbAllfrombook(highSM, highCBS);
							toast(queryTOgird,resultnumdetails);
						}
						//作者和出版社的組合查詢
						else if((highSM.equals(""))&&(!highZZ.equals(""))&&(!highCBS.equals("")))
						{
							queryTOgird=DBUtil.getAuCbAllfrombook(highZZ, highCBS);
							toast(queryTOgird,resultnumdetails);
						}
						//書名作者出版社三者的組合查詢
						else if((!highSM.equals(""))&&(!highZZ.equals(""))&&(!highCBS.equals("")))
						{
							queryTOgird=DBUtil.getBnAuCbAllfrombook(highSM, highZZ, highCBS);
							toast(queryTOgird,resultnumdetails);
						}
						else 
						{
							Toast.makeText
							(
									RootActivity.this,
									"輸入最多一個為空，請輸入要查詢的內容!", 
									Toast.LENGTH_SHORT
							).show();
						}
					}										
				}
        		
        	}
        );
        
        
        
        curr=WhichView.QUERY_VIEW;
    }
    
    public void gotogirdView()
    {
    	setContentView(R.layout.grid_main);      	    	
		GridView gv=(GridView)findViewById(R.id.GridView01);
		generateDataList(queryTOgird,resultnumdetails);
		gv.setAdapter(gridView(queryTOgird,resultnumdetails));
		//resultnumdetails.clear();
		
		//設置選項選中的監聽器
        gv.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//重寫選項被選中事件的處理方法
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        ); 
        
      
        
        //設置選項被單擊的監聽器
        gv.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//重寫選項被單擊事件的處理方法
				//TextView tv=(TextView)findViewById(R.id.girdTextView);//獲取主界面TextView
				LinearLayout ll=(LinearLayout)arg1;//獲取當前選中選項對應的LinearLayout
				LinearLayout l2=(LinearLayout)ll.getChildAt(0);				
				TextView tvn=(TextView)l2.getChildAt(1);
				TextView tvn1=(TextView)l2.getChildAt(3);
				LinearLayout l3=(LinearLayout)ll.getChildAt(1);	
				TextView tvn2=(TextView)l3.getChildAt(1);
				LinearLayout l4=(LinearLayout)ll.getChildAt(2);	
				TextView tvn3=(TextView)l4.getChildAt(1);
				LinearLayout l5=(LinearLayout)ll.getChildAt(3);	
				TextView tvn4=(TextView)l5.getChildAt(1);							
				detailsSelect.add(tvn.getText().toString().trim());
				detailsSelect.add(tvn1.getText().toString().trim());
				detailsSelect.add(tvn2.getText().toString().trim());
				detailsSelect.add(tvn3.getText().toString().trim());
				detailsSelect.add(tvn4.getText().toString().trim());				
				gotodetaislView();								
			}        	   
           }
        );
        
        ImageButton gridButtonback=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonquery_qrid01);
        gridButtonback.setOnClickListener(
        	new OnClickListener()
        	{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
        		
        	}
        );
        
        curr=WhichView.GIRD_VIEW;
    }
    
    public void gotodetaislView()
    {
    	setContentView(R.layout.detials);   	
    	TextView t1=(TextView)RootActivity.this.findViewById(R.id.TextViewdetails01);
    	TextView t2=(TextView)RootActivity.this.findViewById(R.id.TextViewdetails02);
    	TextView t3=(TextView)RootActivity.this.findViewById(R.id.TextViewdetails03);
    	TextView t4=(TextView)RootActivity.this.findViewById(R.id.TextViewdetails04);
    	TextView t5=(TextView)RootActivity.this.findViewById(R.id.TextViewdetails05);
    	TextView t6=(TextView)RootActivity.this.findViewById(R.id.TextViewdetails06);
    	t1.setText(detailsSelect.get(0).toString());
    	t2.setText(detailsSelect.get(1).toString());
    	t3.setText(detailsSelect.get(2).toString());
    	t4.setText(detailsSelect.get(3).toString());
    	t5.setText(detailsSelect.get(4).toString());
    	detailsSelect.clear();
    	String BookISBNgirdTOdetails=t1.getText().toString().trim();			    
    	Vector<String> bookdetails=DBUtil.selectISBNALlfromdetailInfo(BookISBNgirdTOdetails);
    	StringBuilder sb=new StringBuilder();		
		sb.append("                 ");
		sb.append(bookdetails.get(3).toString());//獲取描述信息	
    	t6.setText(sb);
    	
    	GridView gvv=(GridView)findViewById(R.id.GridViewdetails01);
		generateDataList1(bookdetails,queryTOgird);
		gvv.setAdapter(gridView1(bookdetails,queryTOgird));
    	
		//設置後退按鈕的監聽
		ImageButton detailsButtonback=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonquery_details01);
		detailsButtonback.setOnClickListener(
			new OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
				
			}
		);
		//tvdetails.setText(girddetail.get(2));
		curr=WhichView.DETIALSVIEW;
    }
    
    //個人借書的要調用的方法
    public List<? extends Map<String, ?>> selfgenerateDataList(Vector<String> v)
     		{
     	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
     	    	int rowCounter=v.size()/7;//得到表格的行數
     	    	for(int i=0;i<rowCounter;i++)
     	    	{//循環生成每行的包含對應各個列數據的Map；col1、col2、col3為列名
     	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
     	    		hmap.put("col1", v.get(i*7+0));   //第一列為書號		
     	    		hmap.put("col2", v.get(i*7+3));//第二例為ISBN
     	    		hmap.put("col3", v.get(i*7+4));//第三列為書名
     	    		hmap.put("col4", v.get(i*7+5));//第四列為作者
     	    		hmap.put("col5", v.get(i*7+6));//第五列為出版社
     	    		hmap.put("col6", v.get(i*7+1));//第五列為出版社
     	    		hmap.put("col7", v.get(i*7+2));//第五列為出版社
     	    		list.add(hmap);
     	    	}    	
     	    	return list;
     		}
    public List<? extends Map<String, ?>> generateDataList1(Vector<String> v,Vector<String> vv)
    		{
    	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
    	    	int rowCounter=v.size()/4;//得到表格的行數
    	    	for(int i=0;i<rowCounter;i++)
    	    	{//循環生成每行的包含對應各個列數據的Map；col1、col2、col3為列名
    	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
    	    		hmap.put("col1", v.get(i*4+0));   //第一列為書號	
    	    		hmap.put("col2", v.get(i*4+1));//第二例書名   	    		
    	    		hmap.put("col3", v.get(i*4+2));//第四列是否借閱    	    		  	    		
    	    		list.add(hmap);
    	    	}    	
    	    	return list;
    		}
    public BaseAdapter selfgridView(Vector<String> v)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          selfgenerateDataList(v), //數據List
          R.layout.self_query_info, //行對應layout id
          new String[]{"col1","col2","col3","col4","col5","col6","col7"}, //列名列表
          new int[]{R.id.self_detailTextView02,R.id.self_detailTextView04,R.id.self_detailTextView06,R.id.self_detailTextView08,R.id.self_detailTextView10,R.id.self_detailTextView12,R.id.self_detailTextView14}//列對應控件id列表
        );
    	return sca;
       // gv.setAdapter(sca);//為GridView設置數據適配器    	    	
    }
    public BaseAdapter gridView1(Vector<String> v,Vector<String> vv)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          generateDataList1(v,vv), //數據List
          R.layout.details_grid_row, //行對應layout id
          new String[]{"col1","col2","col3"}, //列名列表
          new int[]{R.id.TextViewdetail01,R.id.TextViewdetail02,R.id.TextViewdetail03}//列對應控件id列表
        );
    	return sca;
       // gv.setAdapter(sca);//為GridView設置數據適配器    	    	
    }
    public List<? extends Map<String, ?>> generateDataList(Vector<String> v,Vector<Integer> num)
    		{
    	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
    	    	int rowCounter=v.size()/4;//得到表格的行數
    	    	for(int i=0;i<rowCounter;i++)
    	    	{//循環?�蟛桹棱餼��W�t雜Ω韝雋惺�貹O脾ap；col1、col2、col3為列名
    	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
    	    		hmap.put("col1", v.get(i*4+0));   //第一列為ISBN		
    	    		hmap.put("col2", num.get(i));//第二例為一種ISBN書的數量
    	    		hmap.put("col3", v.get(i*4+1));//第三列為書名
    	    		hmap.put("col4", v.get(i*4+2));//第四列為作者
    	    		hmap.put("col5", v.get(i*4+3));//第五列為出版社
    	    		//hmap.put("col6", v.get(i*6+5));//第六列列為是否預覽
    	    		list.add(hmap);
    	    	}    	
    	    	return list;
    		}
    public BaseAdapter gridView(Vector<String> v,Vector<Integer> result)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          generateDataList(v,result), //數據List
          R.layout.gridview, //行對應layout id
          new String[]{"col1","col2","col3","col4","col5"}, //列名列表
          new int[]{R.id.TextView02,R.id.TextView04,R.id.TextView06,R.id.TextView08,R.id.TextView10}//列對應控件id列表
        );
    	return sca;
       // gv.setAdapter(sca);//為GridView設置數據適配器    	    	
    }
    public void toast()
    {    	
		Toast.makeText
		(
			RootActivity.this,
			"沒有查到與你輸入相關的資料！", 
			Toast.LENGTH_SHORT
		).show();										 
    }
    public void toast(Vector<String> v,Vector<Integer> vv)
    {
    	if(v.size()==0)
		{
			toast();								
		 }
		else 
		{
			for(int i=0;i<v.size()/4;i++)
	        {
		         vv.add(DBUtil.getNumfrombdetailedInfo(v.get(i*4).toString().trim()));
	        }							    
	        gotogirdView();
		}
    	
    }
      
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent e)
    {
    	if(keyCode==4)//調製上一個界面的鍵
    	{//根據記錄的當前是哪個界面信息的curr可以知道要跳轉到的是哪個界面
    		goback();
    	}
    	return false;
    	
    }
    
    
    
}









