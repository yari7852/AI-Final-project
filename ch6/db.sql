

create table book
(
   ISBN varchar(20) primary key,
   B_Name varchar(50),
   B_Author varchar(50),   
   B_Publishment varchar(80),
   B_BuyTime varchar(50)
);
insert into book values('9787302164289','3ds max 9�T���ؼ�','�{��S','�M�ؤj�ǥX����','2010-02-01');
insert into book values('9787121060953','photoshop cs3 �Ϲ��B�z','���V���','�q�l�u�~�X����','2003-02-01');
insert into book values('9787121102462','Java�{�ǭ�¾������-�q�p�u��M�a','�d�Ȯp','�q�l�u�~�X����','2004-04-05');
insert into book values('9787115227508','Android 2.0�C���}�o����_��','�d�Ȯp','�H���l�q�X����','2003-06-07');
insert into book values('9787030236630','PowerBuilder_10.5��αе{','�Ԫ���','��ǥX����','2005-07-05');
insert into book values('9787121079528','PowerBuilder ��αе{�]��3���^','�G���_','�q�l�u�~�X����','2005-07-09');
insert into book values('9787302244158','�j�ǭp�����¦�]21�@�����q���խp������@�ҵ{�W���Ч��^','�\���A���F�� ','�M�ؤj�ǥX����','2005-0-09');
insert into book values('9787562324560','�L���p�����z������','�d�a���A�f�v�P','�ثn�z�u�j�ǥX����','2005-07-09');
insert into book values('9787111187776','��k�ɽ�','�]���^��ҡ]Cormen,T.H.�^ ���ۡA����Q ��Ķ','����u�~�X����','2005-07-09');



create table bdetailedinformation
(
   B_Num varchar(20) primary key,      
   ISBN varchar(20),     
   Borrowed varchar(50),
   Ordered varchar(50),
   Introduction varchar(1000)
);


insert into bdetailedinformation values('10001','9787302164289','�O','�_','���ѳq�L�q�U��������ҸѪR,�q²��ؼҨ�����ʵe���s�@,�v�B�NŪ�̤ޤJ�ʵe�]�p��');

insert into bdetailedinformation values('10002','9787302164289','�O','�_','���ѳq�L�q�U��������ҸѪR,�q²��ؼҨ�����ʵe���s�@,�v�B�NŪ�̤ޤJ�ʵe�]�p��');

insert into bdetailedinformation values('10003','9787302164289','�_','�_','���ѳq�L�q�U��������ҸѪR,�q²��ؼҨ�����ʵe���s�@,�v�B�NŪ�̤ޤJ�ʵe�]�p��');

insert into bdetailedinformation values('10004','9787302164289','�_','�_','���ѳq�L�q�U��������ҸѪR,�q²��ؼҨ�����ʵe���s�@,�v�B�NŪ�̤ޤJ�ʵe�]�p��');

insert into bdetailedinformation values('10005','9787121060953','�_','�_','���Ѥ��e�s�o,�����M��,�y���L�����,�ާ@�|���״I,�C���H��������+��Ժt�m+�W���m�߬����Ѻc');

insert into bdetailedinformation values('10006','9787121060953','�O','�_','���Ѥ��e�s�o,�����M��,�y���L�����,�ާ@�|���״I,�C���H��������+��Ժt�m+�W���m�߬����ѵ��c');

insert into bdetailedinformation values('10007','9787121102462','�_','�_','���ѥH�]ù�U�H��IT�j���򬰭I��,�NJava¾�����q�J���e���Ǯյ泾�������޳N�j�����L�{�i�{��Ū��,���e�������S���������.�b���Ѥ��J���J¾�e����~�����~�ϱư�,�]���J¾�᪺�f�Z�Ǳ©M����s�U�L������');

insert into bdetailedinformation values('10008','9787121102462','�O','�_','���ѥH�]ù�U�H��IT�j���򬰭I��,�NJava¾�����q�J���e���Ǯյ泾�������޳N�j�����L�{�i�{��Ū��,���e�������S���������.�b���Ѥ��J���J¾�e����~�����B�~�ϱư�,�]���J¾�᪺�f�Z�Ǳ©M����s�U�L������');


insert into bdetailedinformation values('10009','9787115227508','�O','�_','���ѥHAndroid����C�����}�o���D�D,���X�u�ꪺ�C���רҦVŪ�̸ԲӤ��ФFAndroid���x�U�C���}�o����Ӭy�{,�P�ɦb�C���}�o�����йL�{���٤��ɤF���̦h�~�n�֪��}�o�ޥ��P�g��');

insert into bdetailedinformation values('10010','9787115227508','�O','�_','���ѥHAndroid����C�����}�o���D�D,���X�u�ꪺ�C���רҦVŪ�̸ԲӤ��ФFAndroid���x�U�C���}�o����Ӭy�{,�P�ɦb�C���}�o�����йL�{���٤��ɤF���̦h�~�n�֪��}�o�ޥ��P�g��');

insert into bdetailedinformation values('10011','9787030236630','�O','�_','���Ѩt�δy�z�FPowerBuilder 10.5�����զ��Ψ�y��,�ƥ�M���,��c��PowerBuilder���U�عﹳ(����,���f,���,����,�ƾڵ��f,�Τ�ﹳ)�H�Υ��̪��ЫةM�ϥΤ�k�����e���F�Բ�����');

insert into bdetailedinformation values('10012','9787030236630','�_','�_','���Ѩt�δy�z�FPowerBuilder 10.5�����զ��Ψ�y��,�ƥ�M���,��c��PowerBuilder���U�عﹳ(����,���f,���,����,�ƾڵ��f,�Τ�ﹳ)�H�Υ��̪��ЫةM�ϥΤ�k�����e���F�Բ�����');


insert into bdetailedinformation values('10013','9787030236630','�_','�_','���Ѩt�δy�z�FPowerBuilder 10.5�����զ��Ψ�y��,�ƥ�M���,��c��PowerBuilder���U�عﹳ(����,���f,���,����,�ƾڵ��f,�Τ�ﹳ)�H�Υ��̪��ЫةM�ϥΤ�k�����e���F�Բ�����');
insert into bdetailedinformation values('10014','9787030236630','�_','�_','���Ѩt�δy�z�FPowerBuilder 10.5�����զ��Ψ�y��,�ƥ�M���,��c��PowerBuilder���U�عﹳ(����,���f,���,����,�ƾڵ��f,�Τ�ﹳ)�H�Υ��̪��ЫةM�ϥΤ�k�����e���F�Բ�����');




insert into bdetailedinformation values('10015','9787121079528','�_','�_','���ѥ]�t��αе{,���D,�W���ާ@���ɩM��X���ι�ߵ�4����.��αе{����t�Φa���ФFPowerBuilder 9.0�}�o����,PowerScript�y��,���f�ε��f����,�Ыؼƾڮw,�ƾڵ��f��H�α���,�Τ�۩w�q��H�Ψƥ��ƩM���c,���,SQL�y�y,���,�ƾں޹D,PBL�w�޲z����');



insert into bdetailedinformation values('10016','9787121079528','�_','�_','���ѥ]�t��αе{,���D,�W���ާ@���ɩM��X���ι�ߵ�4����.��αе{����t�Φa���ФFPowerBuilder 9.0�}�o����,PowerScript�y��,���f�ε��f����,�Ыؼƾڮw,�ƾڵ��f��H�α���,�Τ�۩w�q��H�Ψƥ��ƩM���c,���,SQL�y�y,���,�ƾں޹D,PBL�w�޲z����');




insert into bdetailedinformation values('10017','9787121079528','�_','�_','���ѥ]�t��αе{,���D,�W���ާ@���ɩM��X���ι�ߵ�4����.��αе{����t�Φa���ФFPowerBuilder 9.0�}�o����,PowerScript�y��,���f�ε��f����,�Ыؼƾڮw,�ƾڵ��f��H�α���,�Τ�۩w�q��H�Ψƥ��ƩM���c,���,SQL�y�y,���,�ƾں޹D,PBL�w�޲z����');



insert into bdetailedinformation values('10018','9787121079528','�_','�_','���ѥ]�t��αе{,���D,�W���ާ@���ɩM��X���ι�ߵ�4����.��αе{����t�Φa���ФFPowerBuilder 9.0�}�o����,PowerScript�y��,���f�ε��f����,�Ыؼƾڮw,�ƾڵ��f��H�α���,�Τ�۩w�q��H�Ψƥ��ƩM���c,���,SQL�y�y,���,�ƾں޹D,PBL�w�޲z����');



insert into bdetailedinformation values('10019','9787302244158','�_','�_','���ѬJ�`���򥻭�z�M��k���ĭz,�S�`������O�����i,�H�z�׻P���۵��X���覡���i�ǥͪ����ί�O.');


insert into bdetailedinformation values('10020','9787302244158','�O','�_','���ѬJ�`���򥻭�z�M��k���ĭz,�S�`������O�����i,�H�z�׻P���۵��X���覡���i�ǥͪ����ί�O.');


insert into bdetailedinformation values('10021','9787302244158','�O','�_','���ѬJ�`���򥻭�z�M��k���ĭz,�S�`������O�����i,�H�z�׻P���۵��X���覡���i�ǥͪ����ί�O.');

insert into bdetailedinformation values('10022','9787562324560','�_','�_','���ѥ����`�J�a���ФF�L���p������򥻲զ�,�u�@��z�M�������.���Ѧ@10��,�`�Ǻ��i�a���ФF�L���p������򥻪���,�q8086��19entium 4�L�B�z�����������c�M�S�I,�M�}�覡,���O�t�ΤηJ�s�y���{�ǳ]�p,�b����s�x��,8086���_�t��,��J�P��X���f�޳N,MSC-51��������\��Ψ��X�i��k');


insert into bdetailedinformation values('10023','9787562324560','�_','�_','���ѥ����`�J�a���ФF�L���p������򥻲զ�,�u�@��z�M�������.���Ѧ@10��,�`�Ǻ��i�a���ФF�L���p������򥻪���,�q8086��19entium 4�L�B�z�����������c�M�S�I,�M�}�覡,���O�t�ΤηJ�s�y���{�ǳ]�p,�b����s�x��,8086���_�t��,��J�P��X���f�޳N,MSC-51��������\��Ψ��X�i��k');


insert into bdetailedinformation values('10024','9787111187776','�O','�_','���Ѳ`�J�Q�צU����k,�õۤO�ϳo�Ǻ�k���]�p�M���R�ର�U�Ӽh����Ū�̱���.�U���ۦ���t,�i�H�@���W�ߪ��ǲ߳椸�C��k�H�^�y�M���N�X���Φ��y�z,��ƪ�B�{�ǳ]�p�g�窺�H�N�����.�����M�����O�D�L�����,�����`�שM�ƾ��Y�ԩ�');


insert into bdetailedinformation values('10025','9787111187776','�O','�_','���Ѳ`�J�Q�צU����k,�õۤO�ϳo�Ǻ�k���]�p�M���R�ର�U�Ӽh����Ū�̱���.�U���ۦ���t,�i�H�@���W�ߪ��ǲ߳椸�C��k�H�^�y�M���N�X���Φ��y�z,��ƪ�B�{�ǳ]�p�g�窺�H�N�����.�����M�����O�D�L�����,�����`�שM�ƾ��Y�ԩ�');

insert into bdetailedinformation values('10026','9787111187776','�O','�_','���Ѳ`�J�Q�צU����k,�õۤO�ϳo�Ǻ�k���]�p�M���R�ର�U�Ӽh����Ū�̱���.�U���ۦ���t,�i�H�@���W�ߪ��ǲ߳椸�C��k�H�^�y�M���N�X���Φ��y�z,��ƪ�B�{�ǳ]�p�g�窺�H�N�����.�����M�����O�D�L�����,�����`�שM�ƾ��Y�ԩ�');



create table student
(
  S_Num varchar(20) primary key,
  S_Name varchar(50),
  S_Age varchar(10),
  S_Sex varchar(50),
  S_Class varchar(50),
  S_Department varchar(50),
  S_Phone varchar(11),
  S_Permitted varchar(50),
  S_Pwd varchar(20)
);

insert into student values('10001','����','20','�k','�p���1�Z','�p����t','15176536034','�O','001');
insert into student values('10002','����',21,'�k','�p���1�Z','�p����t','13730220123','�O','002');
insert into student values('10003','�]�n',20,'�k','�p���1�Z','�p����t','13633654578','�O','003');
insert into student values('10004','���',22,'�k','�p���1�Z','�p����t','2578975','�O','004');
insert into student values('10005','���',21,'�k','�p���1�Z','�p����t','13936968956','�O','005');
insert into student values('10006','���z��',20,'�k','�p���2�Z','�p����t','1234667','�O','006');



create table record
(
   B_Num varchar(50) primary key,
   S_Num varchar(20),
   BorrowTime varchar(50),
   ReturnTime varchar(50),
   Borrowed varchar(50),
   Ordered varchar(50)
);

insert into record values('10001','10001','2010-1-2','2010-3-2','�O','�_');
insert into record values('10002','10001','2010-1-23','2010-3-23','�O','�_');
insert into record values('10006','10001','2010-1-2','2010-3-2','�O','�_');
insert into record values('10008','10002','2010-1-2','2010-3-2','�O','�_');
insert into record values('10009','10001','2010-1-2','2010-3-2','�O','�_');

insert into record values('10010','10002','2010-1-2','2010-3-2','�O','�_');
insert into record values('10011','10001','2010-2-2','2010-4-2','�O','�_');
insert into record values('10020','10002','2010-1-2','2010-3-2','�O','�_');
insert into record values('10021','10001','2010-2-2','2010-4-2','�O','�_');
insert into record values('10025','10002','2010-1-2','2010-3-2','�O','�_');
insert into record values('10024','10002','2010-1-2','2010-3-2','�O','�_');
insert into record values('10026','10002','2010-1-2','2010-3-2','�O','�_');




create table orderbook
(
   B_Num varchar(50) primary key,
   S_Name varchar(50),
   S_Class varchar(50),
   B_Name varchar(50),
   S_Num varchar(20),
   B_Author varchar(50)
);

create table losebook
(
   GSBH int primary key,
   B_Num varchar(50), 
   B_Name varchar(50),
   S_Num varchar(20)
);



create table manager
(
   M_Num varchar(20) primary key,
   M_Permitted varchar(50),
   M_Pwd varchar(50)
);
insert into manager values('456','����','123');



create table overtime
(
   S_Num varchar(20),
   B_Num varchar(20),
   B_Name varchar(50),
   overtime int(20),
   primary key(S_Num,B_Num)
);


insert into overtime values('10002','10020','�j�ǭp�����¦�]21�@�����q���խp������@�ҵ{�W���Ч��^',null);
insert into overtime values('10002','10024','��k�ɽ�',null);
insert into overtime values('10002','10025','��k�ɽ�',null);





