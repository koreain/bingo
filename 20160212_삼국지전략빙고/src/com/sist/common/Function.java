package com.sist.common;

public class Function {
   public static final int LOGIN=100;
   public static final int MYLOG=110; //로그인 => 대기실 화면으로 바꿔줄때
   public static final int SAMELOGIN=120; //같은 아이디/비번 로그인시 차단..
   public static final int POSCHANGE=150;
   
   // 방
   public static final int MAKEROOM=200;
   public static final int MYROOMIN=210; //방 들어가는 사람
   public static final int ROOMIN=220;//방 안에 있는 사람이 들어온 사람 정보를 볼수있게 처리
   public static final int ROOMOUT=230;//
   public static final int MYROOMOUT=240;//
   public static final int WAITUPDATE=250;//대기실 정보갱신
   public static final int BANGCHANGE=260;
   
   //방안
   public static final int KANG=300;
   public static final int INVITE=310;
   
   //채팅
   public static final int WAITCHAT=400;
   public static final int ROOMCHAT=410;
   public static final int GAMECHAT=420;
   
   //기타
   public static final int SENDMSG=500;//쪽지보내기
   public static final int INFO=510;//정보보기
   
   //GAME 관련
   public static final int GAMEREQUEST=600;
   public static final int GAMENO=610;
   public static final int GAMEYES=620;
   public static final int GAMEREADY=630;
   public static final int GAMESTART=640;
   
   public static final int GAMESEND=680;
   public static final int GAMETURN=690;
   
   public static final int GAMERESULT=650;
   public static final int GAMEEND=660;
   public static final int MYGAMEEND=670;
   
   //인게임
   public static final int MYTURN=700;
   public static final int YOURTURN=710;
   
   public static final int CHOICENATION=720;
   
   public static final int MYBINGOCHECK=730;
   public static final int YOURBINGOCHECK=740;
   
   public static final int MYATTSKILL=750;
   public static final int MYDEFSKILL=760;
   public static final int MYTRICKSKILL=770;
   public static final int MYATTFURY=780;
   public static final int MYDEFFURY=790;
   public static final int MYTRICKFURY=800;
   
   public static final int YOURATTSKILL=750;
   public static final int YOURDEFSKILL=760;
   public static final int YOURTRICKSKILL=770;
   public static final int YOURATTFURY=780;
   public static final int YOURDEFFURY=790;
   public static final int YOURTRICKFURY=800;
   
   public static final int MYBINGOEND=810;
   public static final int YOURBINGOEND=820;
   
   //종료
   public static final int CHATEND=900;
   public static final int MYCHATEND=910;
   public static final int EXIT=1000; //완전나가기
   public static final int MYEXIT=2000; //완전나가기
}



















