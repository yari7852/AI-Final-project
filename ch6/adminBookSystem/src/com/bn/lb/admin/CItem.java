package com.bn.lb.admin;

public class CItem {
	private String ID = "";
	 private String Value = "";

	 public CItem () {
	  ID = "";
	  Value = "";
	 }

	 public CItem (String _ID, String _Value) {
	  ID = _ID;
	  Value = _Value;
	 }

	 @Override
	 public String toString() {           //ΪʲôҪ��дtoString()�أ���Ϊ����������ʾ���ݵ�ʱ����������������Ķ������ַ���������£�ֱ�Ӿ�ʹ�ö���.toString()
	  // TODO Auto-generated method stub
	  return Value;
	 }

	 public String GetID() {
	  return ID;
	 }

	 public String GetValue() {
	  return Value;
	 }
}
