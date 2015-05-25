package com.bn.lb.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DBUtil 
{
    public static Connection getConnection()
	{
		Connection con=null;
		try
		{			
			Class.forName("org.gjt.mm.mysql.Driver");
			con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/test?useUnicode=true&characterEncoding=UTF-8","root","initial");  		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
    
    
    //���D�ǥͪ��Ǹ��o��L���K�X
    public static String selectPwd(String S_Num)
    {
		String result=null;
		try
		{
			Connection con=getConnection();			
			Statement st=con.createStatement();
			String sql="select S_Pwd from student where S_Num='"+S_Num+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				result=rs.getString(1);
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return result;   	
    }
    
    
    //�o�챾���ϮѪ��H�������O�����ƶq
    public static int getMaxLBNO()
    {
		int result=0;
		try
		{
			Connection con=getConnection();			
			Statement st=con.createStatement();
			String sql="select MAX(GSBH) from losebook";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				result=rs.getInt(1);
			}
			rs.close();
			st.close();
			con.close();
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
    //����S����^�Ȫ����J�y�y����k
    public static void update(String sql)
    {
    	try
		{
			Connection con=getConnection();			
			Statement st=con.createStatement();			
			st.executeUpdate(sql);						
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
          
    
    //�w���ѦW�A�o��o�Ӯ��y���򥻫H��
    public static Vector<String> selectAllfrombook(String BookName)
    {
    	Vector<String> v =new Vector<String>();
    	int lenght=0;
    	try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Name like '%"+BookName+"%'";
			ResultSet rs=st.executeQuery(sql);				
			while(rs.next()){//�N���G���H���K�[���^�V�q��								
				//String[] middle=new String[6];				
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));				
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return v; 
    } 
    
   //�o��P��ISBN�����y���ƶq
    public static int getNumfrombdetailedInfo(String ISBN)
    {
		int num=0;
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select count(B_Num) from bdetailedinformation where ISBN='"+ISBN+"'";
			ResultSet rs=st.executeQuery(sql);						
			if(rs.next()){//�N���G���H���K�[���^�V�q��												
				num=rs.getInt(1);
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return num;
    	
    }
  
  //�@��ISBN���o��P�ظ��U���o�˪��Ѫ��򥻫H��
    public static Vector<String> selectISBNALlfromdetailInfo(String ISBN)
    {
    	Vector<String> v =new Vector<String>();
    	int lenght=0;
    	try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select B_Num,Borrowed,Ordered,Introduction from bdetailedinformation where ISBN='"+ISBN+"'";
			ResultSet rs=st.executeQuery(sql);				
			while(rs.next()){//�N���G���H���K�[���^�V�q��								
				//String[] middle=new String[6];				
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));				
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;    	    	
    }
    
    
    //�ھڮѸ��o��@�̦W
    public static String getAuthor(String BookNO)
    {
		String result=null;
		try
		{
			Connection con=getConnection();			
			Statement st=con.createStatement();			
			String sql="select B_Author from book where B_Num='"+BookNO+"'";
			ResultSet rs=st.executeQuery(sql);			
			if(rs.next())
			{
				result=rs.getString(1);
			}
			System.out.println(result);
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return result;
    	
    }
    
   
    //�q�L��J�ϮѪ��@�̱o��ϮѪ��򥻫H��
    public static Vector<String> getAuthorAllfromBook(String Author)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Author like '%"+Author+"%'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));				
			}
			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	
    }
    
    
    //�q�L�X�����o��ϮѪ��򥻫H��
    public static Vector<String> getPubAllfrombook(String Publishment)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Publishment like '%"+Publishment+"%'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��				
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));							
			}
			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	

    }
    
    
    //�q�L�ѦW�M�@�̱o��ϮѪ��򥻫H��
    public static Vector<String> getBnAuAllfrombook(String BookName,String Author)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Name like '%"+BookName+"%' and B_Author like '%"+Author+"%'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));								
			}
			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	
    }
    
    //�q�L�ѦW�M�X�����o��ϮѪ��򥻫H��
    public static Vector<String> getBnCbAllfrombook(String BookName,String Publishment)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Name like '%"+BookName+"%' and B_Pub like '%"+Publishment+"%'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));								
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	
    }
    
    //�q�L�@�̩M�X����
    public static Vector<String> getAuCbAllfrombook(String Author,String Publishment)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Author like '%"+Author+"%' and B_Pub like '%"+Publishment+"%'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));								
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	
    }
    
    //�q�L�@�� �ѦW�M�X�����i��d��
    public static Vector<String> getBnAuCbAllfrombook(String BookName,String Author,String Publishment)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_name like '%"+BookName+"%' and B_Author like '%"+Author+"%' and B_Pub like '%"+Publishment+"%'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));								
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	
    }
    
    //�q�L�Ѹ���ISBN�M�Ϯ�²�����d��
    public static Vector<String> getISinfromdetails(String BookNo)
    {
    	Vector<String> v =new Vector<String>();    	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,Borrowed,Ordered,Introduction from bdetailedinformation where B_Num='"+BookNo+"'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;     	    	
    }
    
   //�q�LISBN��P�@��ISBN���U���򥻫H��
    public static Vector<String> getISfrombook(String isbn)
    {
    	Vector<String> v =new Vector<String>();   	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where ISBN ='"+isbn+"'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;      	    	
    }
    
    //�ھھǥͪ�ID�o��L�w���ϮѪ��򥻫H��
    public static Vector<String> getBNofromOrder(String stuNo)
    {
    	Vector<String> v =new Vector<String>();   	
    	try
		{
    	//���զb��x���L   		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select B_Num,S_Name,S_num,B_Author from orderbook where S_Num ='"+stuNo+"'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
			}			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return v;      	    	
    }
    //�ھڹw���ϮѫH����o��Y�P�Ǫ��w���ϮѫH��
    public static int getNumfromborderreport(String stuno)
    {
		int num=0;
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select count(B_Num) from orderbook where B_Num='"+stuno+"'";
			ResultSet rs=st.executeQuery(sql);						
			if(rs.next()){//�N���G���H���K�[���^�V�q��												
				num=rs.getInt(1);
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return num;
    	
    }
    
    //�ھھǥͪ��Ǹ��o��ϮѪ�ISBN�ABookNO,BookName,Author,Publishment,�ɾ\�ɶ��A�k�ٮɶ�
    public static Vector<String> getSomeInfo(String stuno)
    {
    	Vector<String> result=new Vector<String>();
		try
		{   	
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select record.B_Num,record.BorrowTime,record.ReturnTime,book.ISBN,book.B_Name,book.B_Author,book.B_Pub from record,book,bdetailedinformation where record.B_Num=bdetailedinformation.B_Num AND bdetailedinformation.ISBN=book.ISBN And record.S_Num='"+stuno+"'";
			ResultSet rs=st.executeQuery(sql);
			int num=0;
			while(rs.next()){//�N���G���H���K�[���^�V�q��												
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));
				result.add(rs.getString(5));
				result.add(rs.getString(6));
				result.add(rs.getString(7));				
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
    
    
    
    //�ھڹϮѪ��Ѹ��o��ϮѪ��򥻫H��
    public static Vector<String> getBNSomeInfo(String BookNO)
    {
    	Vector<String> result=new Vector<String>();
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select record.B_Num,record.BorrowTime,record.ReturnTime,book.ISBN,book.B_Name,book.B_Author,book.B_Publishment from record,book,bdetailedinformation where record.B_Num=bdetailedinformation.B_Num AND bdetailedinformation.ISBN=book.ISBN And record.B_Num='"+BookNO+"'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��												
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));
				result.add(rs.getString(5));
				result.add(rs.getString(6));
				result.add(rs.getString(7));				
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
    
    //�ھڹw���ϮѮѸ��o��ϮѰ򥻫H��
    public static Vector<String> getBNSomeINFO(String BookNO)
    {
    	Vector<String> result=new Vector<String>();
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select orderbook.B_Num,book.ISBN,book.B_Name,book.B_Author,book.B_Publishment,bdetailedinformation.Borrowed from orderbook,book,bdetailedinformation where orderbook.B_Num=bdetailedinformation.B_Num AND bdetailedinformation.ISBN=book.ISBN And orderbook.B_Num='"+BookNO+"'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��												
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));
				result.add(rs.getString(5));
				result.add(rs.getString(6));
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
    
    //�q�L�ǥͪ�ID�o��ǥͪ��Z�šA�m�W�A�Ǹ�
    public static String[] getIDClNO(String stuno)
    {
		String[] result=new String[3];
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select S_Num,S_Class,S_Name from student where S_Num='"+stuno+"'";
			ResultSet rs=st.executeQuery(sql);						
			if(rs.next()){//�N���G���H���K�[���^�V�q��												
				result[0]=rs.getString(1);
				result[1]=rs.getString(2);
				result[2]=rs.getString(3);
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
    
    //�q�L�Ѹ��o���k�ٮɶ�
    public static String gettimefromrecord(String BookNo)
    {
		String result=null;
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ReturnTime from record where B_Num='"+BookNo+"'";
			ResultSet rs=st.executeQuery(sql);						
			if(rs.next()){//�N���G���H���K�[���^�V�q��												
				result=rs.getString(1);				
			}		
			rs.close();
			st.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
    
    
    //�q�L�Ѹ��P�_�ɭԬO�A�ɪ��A
    public static String getifBorrow(String BookNO)
    {
    	String result=null;
		try
		{
    	//���զb��x���L    		
			Connection con=getConnection();				
			Statement st=con.createStatement();
			String sql="select ReturnTime from record where B_Num='"+BookNO+"'";
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()){//�N���G���H���K�[���^�V�q��												
				result=rs.getString(1);						
			}		
			rs.close();
			st.close();
			con.close();
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}		
    	return result;
    	
    }
   
}


