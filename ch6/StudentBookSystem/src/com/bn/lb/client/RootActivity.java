package com.bn.lb.client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import android.R.color;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import com.bn.lb.client.CItem;
import static com.bn.lb.client.Constant.*;




enum WhichView {MAIN_MENU,IP_VIEW,LOSE_VIEW,YUYUE_VIEW,QUERY_VIEW,GIRD_VIEW,DETIALSVIEW,YUYUEDETAILS,YUYUE_MANAGE,LOSE_INFO_VIEW,
	            LOSE_DETAILS_VIEW,MANAGE_DETAILS_VIEW,QUERYMAIN_VIEW,SELF_VIEW_INFO,HELP_VIEW,ABOUT_VIEW}

public class RootActivity extends Activity 
{
	MainMenuView mmv;
	WhichView curr;
	private String sname;//�O�s�n���ɪ��Τ�W
	private Vector<String> queryTOgird =new Vector<String>();	
	private Vector<String> detailsSelect=new Vector<String>();	
	private Vector<Integer> resultnumdetails =new Vector<Integer>();
	private String numberdetails;
	private Vector<String> yuyuedetails1=new Vector<String>();//�w���D�ɭ���ԲӬɭ����ƾکӸ����X
	private Vector<String> yuyuedetails2=new Vector<String>();//�w���D���ɭ���ԲӬɭ����ƾڶ��X
	private Vector<String> yuyueManage1=new Vector<String>();//�w���޲z���ƾڶ��X	
	private String bookno;
	private Vector<String> loseInfo=new Vector<String>();//�����ϮѰ򥻫H�����ƾڶ��X
	private Vector<String> loseInfo1=new Vector<String>();//�����ϮѰ򥻫H�����ƾڶ��X
	private Vector<String> manageInfo=new Vector<String>();//�d�ݹw���ϮѪ��򥻫H��
	private String SnameID;
	private Vector<String> selfQuery=new Vector<String>();//�ӤH�ϮѬd�ߪ��򥻫H��
    private Vector<String> strmanage=new Vector<String>();//�奻�����Ѹ�
    
    
    Handler hd=new Handler() //�����H���ɭ�����
    {
	   @Override
	  public void handleMessage(Message msg)//���g��k  
	  {
		 switch(msg.what)
		 {
		     case 0:
		    	 gotoIpView();  //�n���ɭ�
			    break;
		     case 1:
		    	 gotoQueryMainView();   //�d�߬ɭ�
			    break;
		     case 2:
		    	 gotoloseView();   //�����ɭ�
			    break;
		     case 3:
		    	 gotoyuyueView();  //�w���ɭ�
			    break;
		     case 4:
		    	 goToHelpView();   //���P�ɭ���ipview()
		        break;
		     case 5:
		    	 goToAboutView();
		    	 break;
		 }
	 }
   };	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);        
        //�]�m�������
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );      
        //�j��ݫ�
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
      
        goToWelcomeView();
    }
       
    //���U�ɭ�
    public void goToHelpView()
    {
    	setContentView(R.layout.helpview);   	
    	curr=WhichView.HELP_VIEW;
    }
    
    //����ɭ�
    public void goToAboutView()
    {
    	setContentView(R.layout.about);
    	curr=WhichView.ABOUT_VIEW;
    }
    
    public void goToWelcomeView()
    {
    	MySurfaceView mView=new MySurfaceView(this);
    	setContentView(mView);
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
    
    public void gotoIpView()
    {
    	
    	setContentView(R.layout.main);   
    	final Button dlu=(Button)this.findViewById(R.id.button01);
    	final Button chz=(Button)this.findViewById(R.id.button02);
    	final EditText yhm=(EditText)findViewById(R.id.yhm);
    	final EditText pwd=(EditText)findViewById(R.id.pwd);
    	
    	//�n�������s�]�m����ť
    	dlu.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				    sname=yhm.getText().toString().trim();
					String spwd=pwd.getText().toString().trim();
					String ppwd=DBUtil.selectPwd(sname);
					System.out.println("==========================AAAAAAAAA=========================");
					System.out.print(ppwd);
					if(spwd.equals(ppwd))
					{						
						goToMainMenu();
						
					}
					else 
					{
						Toast.makeText
						(
								RootActivity.this,
								"�n������", 
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
    	
        curr=WhichView.IP_VIEW;
    	
    }
   
    
    
    public void gotoloseView()
    {
    	setContentView(R.layout.lose); 
    	final EditText tvXH=(EditText)RootActivity.this.findViewById(R.id.loseXH);
    	final EditText tvMM=(EditText)RootActivity.this.findViewById(R.id.loseMM);
    	final Button loseButtonOk=(Button)RootActivity.this.findViewById(R.id.losebok);
    	final Button loseButtonRe=(Button)RootActivity.this.findViewById(R.id.loseresert);
    	//����h���s�K�[��ť
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
    	//���m���s���]�m��ť
    	loseButtonRe.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					tvXH.setText("");
					tvMM.setText("");
				}
    			
    		}
    	);
    	loseInfo.clear();
    	//�������s���]�m��ť
    	loseButtonOk.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SnameID=tvXH.getText().toString().trim();
					String mm=tvMM.getText().toString().trim();
					String ppwd=DBUtil.selectPwd(sname);					
					if(mm.equals(ppwd))
					{
						loseInfo=DBUtil.getSomeInfo(SnameID);
						gotoloseinfoView();
					}
					else 
					{
						Toast.makeText
						(
								RootActivity.this,
								"�K�X�M�Ǹ����ǰt�I", 
								Toast.LENGTH_SHORT
						).show();
					}
				}
    			
    		}
    	);
    	   	
    	curr=WhichView.LOSE_VIEW;
    			
		 
    }
    
    public void gotoloseinfoView()
    {
    	setContentView(R.layout.loseinfo);
    	
    	GridView gvlose=(GridView)RootActivity.this.findViewById(R.id.GridViewlose01);
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          generateDataList(loseInfo), //�ƾ�List
          R.layout.lose_grid_row, //�����layout id
          new String[]{"col1","col2","col3","col4","col5"}, //�C�W�C��
          new int[]{R.id.loseTextView02,R.id.loseTextView04,R.id.loseTextView06,R.id.loseTextView08,R.id.loseTextView10}//�C��������id�C��
        );
       
    	gvlose.setAdapter(sca);//��GridView�]�m�ƾھA�t��        
        //�]�m�ﶵ�襤����ť��
    	gvlose.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//���g�ﶵ�Q�襤�ƥ󪺳B�z��k
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        );  
        
        //�]�m�ﶵ�Q��������ť��
    	gvlose.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k				
				LinearLayout l1=(LinearLayout)arg1;//�����e�襤�ﶵ������LinearLayout
				LinearLayout l2=(LinearLayout)l1.getChildAt(0);
				TextView tvn=(TextView)l2.getChildAt(1);//����䤤��TextView �Ѹ�
				String booknum=tvn.getText().toString().trim();
				loseInfo1=DBUtil.getBNSomeInfo(booknum);
				gotolosedetailsView();
			}        	   
           }
        );        
    	//����h�����s�K�[��ť
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
    //�����ɭ����ԲӬɭ�
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
    	
        
    	
    	//�T�w�������s����ť
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
			    	int num=DBUtil.getMaxLBNO()+1;
			    	String sql="insert into losebook values ("+num+",'"+losebook[0]+"','"+losebook[1]+"','"+SnameID+"')";
			    	DBUtil.update(sql);
			    	Toast.makeText
					(
							RootActivity.this,
							"�������\�I", 
							Toast.LENGTH_SHORT
					).show();
				}
    			
    		}
    	);
      //��h���s���]�m��ť
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
    	
    	curr=WhichView.LOSE_DETAILS_VIEW;
    	
    }
     
    public void gotoyuyueView()
    {
    	setContentView(R.layout.yuyue);   	
    	final EditText yuyueEditSH=(EditText)RootActivity.this.findViewById(R.id.yuyueEditSH);
    	Button orderbook=(Button)RootActivity.this.findViewById(R.id.yuyuequery01);//�w���ϮѪ����s
    	Button managebook=(Button)RootActivity.this.findViewById(R.id.yuyuequery02);//�޲z�w�������s
    	//imagebutton��h���s����ť
        ImageButton ibyuyue=(ImageButton)findViewById(R.id.ImageButtonyuyue);
        ibyuyue.setOnClickListener(
        		new OnClickListener()
        		{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						goback();
					}
        			
        		}
        );       
    	
    	
        orderbook.setOnClickListener(
        		new OnClickListener()
        		{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					 bookno=yuyueEditSH.getText().toString().trim();
    					 if(bookno.equals(""))
    					 {
    						 Toast.makeText
    							(
    								RootActivity.this,
    								"�Ѹ����šA�п�J�Ѹ��I", 
    								Toast.LENGTH_SHORT
    							).show();	
    					 }
    					 else
    					 {
    						 yuyuedetails1.clear();
    						 yuyuedetails2.clear();
    						 yuyuedetails1=DBUtil.getISinfromdetails(bookno);
    						 if(yuyuedetails1.size()==0)
    						 {
    							 Toast.makeText
      							(
      								RootActivity.this,
      								"�藍�_�A�A�n�w������Ƥ��s�b�I", 
      								Toast.LENGTH_SHORT
      							).show();
    						 }
    						 else if(yuyuedetails1.get(2).toString().equals("�O"))
    					     {
    					    	 Toast.makeText
     							(
     								RootActivity.this,
     								"�ӮѤw�Q�w���A�п�ܨ�L�Ѹ��I", 
     								Toast.LENGTH_SHORT
     							).show();	
    					     }    					     
    					     else
    					     {
    					          yuyuedetails2=DBUtil.getISfrombook(yuyuedetails1.get(0).toString().trim());
    					          gotoyuyuedetails();
    					     }
    					 }
    				}
        			
        		}
        	);
        
        yuyueManage1.clear();
    	
    	//�޲z�w�������s���]�m��ť
    	managebook.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					yuyueManage1=DBUtil.getBNofromOrder(sname);
					if(yuyueManage1.size()==0)
					{
						 Toast.makeText
							(
								RootActivity.this,
								"�A�S���w���L�ϮѡA�Х��w���ϮѦA�i��޲z�I", 
								Toast.LENGTH_SHORT
							).show();
					}
					else
					{						
						gotoyuyueManage();
					}
				}
    			
    		}
    	);    	
    	curr=WhichView.YUYUE_VIEW;    	    	   	    	   	
    }
    
    public void gotoyuyueManage()
    {
    	setContentView(R.layout.yuyuemanage);
    	
    	TextView tvnum=(TextView)RootActivity.this.findViewById(R.id.yuyuemanageNum);
    	tvnum.setText(yuyueManage1.size()/4+"");
    	GridView gridManage01=(GridView)RootActivity.this.findViewById(R.id.GridViewyuyue01);
    	generateDataList1(yuyueManage1,yuyueManage1);
    	gridManage01.setAdapter(gridView1(yuyueManage1,yuyueManage1));
    	//�]�m�ﶵ�襤����ť��
    	gridManage01.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//���g�ﶵ�Q�襤�ƥ󪺳B�z��k
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        ); 
        
      
    	strmanage.clear();
        //�]�m�ﶵ�Q��������ť��
    	gridManage01.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k
				//TextView tv=(TextView)findViewById(R.id.girdTextView);//����D�ɭ�TextView
				LinearLayout ll=(LinearLayout)arg1;//�����e�襤�ﶵ������LinearLayout				
				TextView l3=(TextView)ll.getChildAt(0);
			    String str1=l3.getText().toString().trim();
				manageInfo=DBUtil.getBNSomeINFO(str1);
				//strmanage=DBUtil.getifBorrow(str1);
				gotomanagedetailsView();																		
			}        	   
           }
        );
    	//��h���s�]�m��h����ť�ƥ�
    	ImageButton imageManage=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonyuyue_manage01);
    	imageManage.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	curr=WhichView.YUYUE_MANAGE;
    }
 
    public void gotomanagedetailsView()
    {
    	
    	if(manageInfo.get(5).toString().equals("�O"))
   	    {
    		setContentView(R.layout.yuyuemanagedetails1);
    		String bytime=DBUtil.getifBorrow(manageInfo.get(0).toString().trim()).toString();
    		TextView tvma=(TextView)RootActivity.this.findViewById(R.id.ordermanageTextV);
    		tvma.setText(bytime);
    	}
    	else 
   	   {
    		setContentView(R.layout.yuyuemanagedetails);
     	}
    	//String ss=DBUtil.getifBorrow(str).toString().trim();	   	
    	TextView tt1=(TextView)RootActivity.this.findViewById(R.id.manage_detailTextView02);
    	TextView tt2=(TextView)RootActivity.this.findViewById(R.id.manage_detailTextView04);
    	TextView tt3=(TextView)RootActivity.this.findViewById(R.id.manage_detailTextView06);
    	TextView tt4=(TextView)RootActivity.this.findViewById(R.id.manage_detailTextView08);
    	TextView tt5=(TextView)RootActivity.this.findViewById(R.id.manage_detailTextView10);
    	tt1.setText(manageInfo.get(0).toString());
    	tt2.setText(manageInfo.get(1).toString());
    	tt3.setText(manageInfo.get(2).toString());
    	tt4.setText(manageInfo.get(3).toString());
    	tt5.setText(manageInfo.get(4).toString());
    	
    	//��^���s����ť�ƥ�
    	Button btd=(Button)RootActivity.this.findViewById(R.id.manage_details_button);
    	btd.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					gotoyuyueManage();
				}
    			
    		}
    	);
    	//imageButton��^����ť�ƥ�
    	ImageButton ibd=(ImageButton)RootActivity.this.findViewById(R.id.details_ImageButtonmanage);
    	ibd.setOnClickListener(
        		new OnClickListener()
        		{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					gotoyuyueManage();
    				}
        			
        		}
        	);
    	
    	curr=WhichView.MANAGE_DETAILS_VIEW;
    }
    public void gotoyuyuedetails()
    {   	
    	if(yuyuedetails1.get(1).toString().trim().equals("�O"))
    	{
    		setContentView(R.layout.yuyuedetails1);
    		LinearLayout lo=(LinearLayout)RootActivity.this.findViewById(R.id.yuyuedetailsLinear);
        	TextView tv07=(TextView)RootActivity.this.findViewById(R.id.yuyuedetailsBrrow);
    		lo.setVisibility(1);    		
    		String str=DBUtil.gettimefromrecord(bookno);
    		tv07.setText(str);
        	TextView tv01=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails01);
        	TextView tv02=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails02);
        	TextView tv03=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails03);
        	TextView tv04=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails04);
        	TextView tv05=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails05);
        	TextView tv06=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails06);   	
        	tv01.setText(bookno);
        	tv02.setText(yuyuedetails2.get(0));
        	tv03.setText(yuyuedetails2.get(1));
        	tv04.setText(yuyuedetails2.get(2));
        	tv05.setText(yuyuedetails2.get(3)); 
        	StringBuilder sb=new StringBuilder();		
    		sb.append("                 ");
    		sb.append(yuyuedetails1.get(3).toString());//����y�z�H��	
        	tv06.setText(sb);
        
        	//�T�w�w�����s���]�m��ť
        	Button yuyueOK=(Button)RootActivity.this.findViewById(R.id.yuyueOK);
        	yuyueOK.setOnClickListener(
        		new OnClickListener()
        		{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					//���U�����s��ܩm�W��J��ܮ�
    				    String[] yuyueInfo=new String[3];
    					yuyueInfo=DBUtil.getIDClNO(sname);//�q�L�n�����ǥͪ�ID�o��o�Ӿǥͪ��򥻫H��
    					String sql="insert into orderbook values('"+bookno+"','"+yuyueInfo[2]+"','"+yuyueInfo[1]+"','"+yuyuedetails2.get(1).toString().trim()+"','"+yuyueInfo[0]+"','"+yuyuedetails2.get(2).toString().trim()+"')";
    					String sql1="update bdetailedinformation set Ordered='�O' where B_Num='"+bookno+"'";
    					DBUtil.update(sql);
    					DBUtil.update(sql1);
    					Toast.makeText 
    					(
    							RootActivity.this,
    							"�w�����\�I", 
    							Toast.LENGTH_SHORT
    					).show();
    				}   			
        		}
        	);
    	}
    	else 
    	{
    	    setContentView(R.layout.yuyuedetails);
    	    TextView tv01=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails01);
        	TextView tv02=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails02);
        	TextView tv03=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails03);
        	TextView tv04=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails04);
        	TextView tv05=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails05);
        	TextView tv06=(TextView)RootActivity.this.findViewById(R.id.TextVieworderdetails06);   	
        	tv01.setText(bookno);
        	tv02.setText(yuyuedetails2.get(0));
        	tv03.setText(yuyuedetails2.get(1));
        	tv04.setText(yuyuedetails2.get(2));
        	tv05.setText(yuyuedetails2.get(3)); 
        	StringBuilder sb=new StringBuilder();		
    		sb.append("                 ");
    		sb.append(yuyuedetails1.get(3).toString());//����y�z�H��	
        	tv06.setText(sb);
        
        	//��^���s���s���]�m��ť
        	Button breturn=(Button)RootActivity.this.findViewById(R.id.yuyueReturn);
        	breturn.setOnClickListener(
        		new OnClickListener()
        		{

    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					gotoyuyueView();
    				}   			
        		}
        	);
    	}
    	
    	
    	//��h���s���]�m��ť
    	ImageButton imagebt=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonyuyue_details01);
    	imagebt.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}
    			
    		}
    	);
    	

    	curr=WhichView.YUYUEDETAILS;
    }
    
    //�ϮѬd�ߥD�ɭ�
    public void gotoQueryMainView()
    {
    	setContentView(R.layout.self_or_query);
    	//��h���s���]�m��ť
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
    	
    	//�d�߹ϮѪ��ɭ�
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
    	
    	//�d�߭ӤH�ϮѬɭ�
    	selfQuery.clear();
    	Button selfb1=(Button)RootActivity.this.findViewById(R.id.self_1_orbutton);
    	selfb1.setOnClickListener(
    		new OnClickListener()
    		{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					selfQuery=DBUtil.getSomeInfo(sname);
					gotoSelfView();
				}
    			
    		}
    	);
    	
    	curr=WhichView.QUERYMAIN_VIEW;
    }
    
    //�ӤH�ϮѪ��d��
    public void gotoSelfView()
    {
    	setContentView(R.layout.self_query);    	
    	//��h���s���]�m��ť
    	ImageButton selfib1=(ImageButton)RootActivity.this.findViewById(R.id.ImageButtonself_details01);
    	selfib1.setOnClickListener(
    		new OnClickListener()
    		{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goback();
				}  			
    		}
    	);
       	//girdView
    	GridView gr=(GridView)RootActivity.this.findViewById(R.id.selfGridViewdetails01);
    	selfgenerateDataList(selfQuery);
		gr.setAdapter(selfgridView(selfQuery));   	
    	curr=WhichView.SELF_VIEW_INFO;
    }
    public void gotoQueryView()
    {
    	setContentView(R.layout.query);
    	final Spinner sp=(Spinner)findViewById(R.id.Spinner01);
    	List<CItem > lst = new ArrayList<CItem>();
        CItem  ct = new CItem ("1","�ѦW");
        CItem  ct1 = new CItem ("2","�@��");
        CItem  ct2 = new CItem ("3","�X����");
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
                        
        //imagebutton��h���s����ť
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
          
       //���²��M���ų����s����������ť����k 
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
        //���ų����s��������ť��k       
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
					//²��d�ߪ��ƥ��ť
					if(simpleq.isChecked())
					{						
						String result=simpleEdit.getText().toString().trim();
						String id=((CItem)sp.getSelectedItem()).GetID().toString().trim();
						
						if(result.equals(""))
						{
							Toast.makeText
							(
									RootActivity.this,
									"��J���ର�šA�п�J�n�d�ߪ����e!", 
									Toast.LENGTH_SHORT
							).show();	
						}
					  
						else 
						{	
							resultnumdetails.clear();
						    if(id.equals("1"))//�q�L��J�ѦW�i��d��
						    {
						    	queryTOgird=DBUtil.selectAllfrombook(result);
						    	toast(queryTOgird,resultnumdetails);															
						     }
						    else if(id.equals("2"))//�q�L�@�̶i��d��
						    {
						    	queryTOgird=DBUtil.getAuthorAllfromBook(result);
						    	toast(queryTOgird,resultnumdetails);
						    }
						   else if(id.equals("3"))//�q�L�X�����i��d��
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
									"��J��������šA�п�J�n�d�ߪ����e!", 
									Toast.LENGTH_SHORT
							).show();	
						}
						//�ѦW�M�@�̪��զX�d��
						else if((!highSM.equals(""))&&(!highZZ.equals(""))&&(highCBS.equals("")))							
						{
							queryTOgird=DBUtil.getBnAuAllfrombook(highSM, highZZ);
							toast(queryTOgird,resultnumdetails);
						}
						//�ѦW�M�X�������զX�d��
						else if((!highSM.equals(""))&&(highZZ.equals(""))&&(!highCBS.equals("")))
						{
							queryTOgird=DBUtil.getBnCbAllfrombook(highSM, highCBS);
							toast(queryTOgird,resultnumdetails);
						}
						//�@�̩M�X�������զX�d��
						else if((highSM.equals(""))&&(!highZZ.equals(""))&&(!highCBS.equals("")))
						{
							queryTOgird=DBUtil.getAuCbAllfrombook(highZZ, highCBS);
							toast(queryTOgird,resultnumdetails);
						}
						//�ѦW�@�̥X�����T�̪��զX�d��
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
									"��J�̦h�@�Ӭ��šA�п�J�n�d�ߪ����e!", 
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
		
		//�]�m�ﶵ�襤����ť��
        gv.setOnItemSelectedListener(
           new OnItemSelectedListener()
           {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {//���g�ﶵ�Q�襤�ƥ󪺳B�z��k
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
    	   
           }
        ); 
        
      
        
        //�]�m�ﶵ�Q��������ť��
        gv.setOnItemClickListener(
           new OnItemClickListener()
           {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {//���g�ﶵ�Q�����ƥ󪺳B�z��k
				//TextView tv=(TextView)findViewById(R.id.girdTextView);//����D�ɭ�TextView
				LinearLayout ll=(LinearLayout)arg1;//�����e�襤�ﶵ������LinearLayout
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
		sb.append(bookdetails.get(3).toString());//����y�z�H��	
    	t6.setText(sb);
    	
    	GridView gvv=(GridView)findViewById(R.id.GridViewdetails01);
		generateDataList1(bookdetails,queryTOgird);
		gvv.setAdapter(gridView1(bookdetails,queryTOgird));
    	
		//�]�m��h���s����ť
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
    
    //�ӤH�ɮѪ��n�եΪ���k
    public List<? extends Map<String, ?>> selfgenerateDataList(Vector<String> v)
     		{
     	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
     	    	int rowCounter=v.size()/7;//�o���檺���
     	    	for(int i=0;i<rowCounter;i++)
     	    	{//�`���ͦ��C�檺�]�t�����U�ӦC�ƾڪ�Map�Fcol1�Bcol2�Bcol3���C�W
     	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
     	    		hmap.put("col1", v.get(i*7+0));   //�Ĥ@�C���Ѹ�		
     	    		hmap.put("col2", v.get(i*7+3));//�ĤG�Ҭ�ISBN
     	    		hmap.put("col3", v.get(i*7+4));//�ĤT�C���ѦW
     	    		hmap.put("col4", v.get(i*7+5));//�ĥ|�C���@��
     	    		hmap.put("col5", v.get(i*7+6));//�Ĥ��C���X����
     	    		hmap.put("col6", v.get(i*7+1));
     	    		hmap.put("col7", v.get(i*7+2));
     	    		list.add(hmap);
     	    	}    	
     	    	return list;
     		}
    public BaseAdapter selfgridView(Vector<String> v)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          selfgenerateDataList(v), //�ƾ�List
          R.layout.self_query_info, //�����layout id
          new String[]{"col1","col2","col3","col4","col5","col6","col7"}, //�C�W�C��
          new int[]{R.id.self_detailTextView02,R.id.self_detailTextView04,R.id.self_detailTextView06,R.id.self_detailTextView08,R.id.self_detailTextView10,R.id.self_detailTextView12,R.id.self_detailTextView14}//�C��������id�C��
        );
    	return sca;
       // gv.setAdapter(sca);//��GridView�]�m�ƾھA�t��    	    	
    }
    
    //�����檺��Ƶ��o�쪺��檺�򥻫H��
     public List<? extends Map<String, ?>> generateDataList(Vector<String> v,Vector<Integer> num)
    		{
    	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
    	    	int rowCounter=v.size()/4;//�o���檺���
    	    	for(int i=0;i<rowCounter;i++)
    	    	{//�`���ͦ��C�檺�]�t�����U�ӦC�ƾڪ�Map�Fcol1�Bcol2�Bcol3���C�W
    	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
    	    		hmap.put("col1", v.get(i*4+0));   //�Ĥ@�C��ISBN		
    	    		hmap.put("col2", num.get(i));//�ĤG�Ҭ��@��ISBN�Ѫ��ƶq
    	    		hmap.put("col3", v.get(i*4+1));//�ĤT�C���ѦW
    	    		hmap.put("col4", v.get(i*4+2));//�ĥ|�C���@��
    	    		hmap.put("col5", v.get(i*4+3));//�Ĥ��C���X����
    	    		//hmap.put("col6", v.get(i*6+5));//�Ĥ��C�C���O�_�w��
    	    		list.add(hmap);
    	    	}    	
    	    	return list;
    		}
     public List<? extends Map<String, ?>> generateDataList(Vector<String> v)
     		{
     	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
     	    	int rowCounter=v.size()/7;//�o���檺���
     	    	for(int i=0;i<rowCounter;i++)
     	    	{//�`���ͦ��C�檺�]�t�����U�ӦC�ƾڪ�Map�Fcol1�Bcol2�Bcol3���C�W
     	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
     	    		hmap.put("col1", v.get(i*7+0));   //�Ĥ@�C���Ѹ�		
     	    		hmap.put("col2", v.get(i*7+3));//�ĤG�Ҭ�ISBN
     	    		hmap.put("col3", v.get(i*7+4));//�ĤT�C���ѦW
     	    		hmap.put("col4", v.get(i*7+5));//�ĥ|�C���@��
     	    		hmap.put("col5", v.get(i*7+6));//�Ĥ��C���X����     	    		
     	    		list.add(hmap);
     	    	}    	
     	    	return list;
     		}
     
     public List<? extends Map<String, ?>> generateDataList1(Vector<String> v,Vector<String> vv)
    		{
    	    	ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();;
    	    	int rowCounter=v.size()/4;//�o���檺���
    	    	for(int i=0;i<rowCounter;i++)
    	    	{//�`���ͦ��C�檺�]�t�����U�ӦC�ƾڪ�Map�Fcol1�Bcol2�Bcol3���C�W
    	    		HashMap<String,Object> hmap=new HashMap<String,Object>();
    	    		hmap.put("col1", v.get(i*4+0));   //�Ĥ@�C���Ѹ�	
    	    		hmap.put("col2", v.get(i*4+1));//�ĤG�ҮѦW   	    		
    	    		hmap.put("col3", v.get(i*4+2));//�ĥ|�C�O�_�ɾ\    	    		  	    		
    	    		list.add(hmap);
    	    	}    	
    	    	return list;
    		}
    
    public BaseAdapter gridView(Vector<String> v,Vector<Integer> result)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          generateDataList(v,result), //�ƾ�List
          R.layout.grid_row, //�����layout id
          new String[]{"col1","col2","col3","col4","col5"}, //�C�W�C��
          new int[]{R.id.TextView02,R.id.TextView04,R.id.TextView06,R.id.TextView08,R.id.TextView10}//�C��������id�C��
        );
    	return sca;
       // gv.setAdapter(sca);//��GridView�]�m�ƾھA�t��    	    	
    }
    
    public BaseAdapter gridView1(Vector<String> v,Vector<String> vv)
    {
    	SimpleAdapter sca=new SimpleAdapter
        (
          this,
          generateDataList1(v,vv), //�ƾ�List
          R.layout.details_grid_row, //�����layout id
          new String[]{"col1","col2","col3"}, //�C�W�C��
          new int[]{R.id.TextViewdetail01,R.id.TextViewdetail02,R.id.TextViewdetail03}//�C��������id�C��
        );
    	return sca;
       // gv.setAdapter(sca);//��GridView�]�m�ƾھA�t��    	    	
    }
          
    
    //��h���s���ƥ��k
       
    public boolean goback()
    {
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
		if(curr==WhichView.YUYUE_VIEW||curr==WhichView.ABOUT_VIEW||curr==WhichView.HELP_VIEW)
		{
			goToMainMenu();	
			return true;
		}
		if(curr==WhichView.YUYUEDETAILS)
		{
			gotoyuyueView();
			return true;
		}
		if(curr==WhichView.YUYUE_MANAGE)
		{
			gotoyuyueView();
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
		if(curr==WhichView.MANAGE_DETAILS_VIEW)
		{
			gotoyuyueManage();
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
    	return false;
    	
    }
    
    //��L�W����^�䪺��ť�ƥ�
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent e)
    {
    	if(keyCode==4)//�ջs�W�@�Ӭɭ�����
    	{//�ھڰO������e�O���Ӭɭ��H����curr�i�H���D�n����쪺�O���Ӭɭ�
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
    		if(curr==WhichView.YUYUE_VIEW||curr==WhichView.ABOUT_VIEW||curr==WhichView.HELP_VIEW)
    		{
    			goToMainMenu();	
    			return true;
    		}
    		if(curr==WhichView.YUYUEDETAILS)
    		{
    			gotoyuyueView();
    			return true;
    		}
    		if(curr==WhichView.YUYUE_MANAGE)
    		{
    			gotoyuyueView();
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
    		if(curr==WhichView.MANAGE_DETAILS_VIEW)
    		{
    			gotoyuyueManage();
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
    		
    	}
    	return false;
    	
    }
    
    public void toast()
    {    	
		Toast.makeText
		(
			RootActivity.this,
			"�S���d��P�A��J��������ơI", 
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
      
}









