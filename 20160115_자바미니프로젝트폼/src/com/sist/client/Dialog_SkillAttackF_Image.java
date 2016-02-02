package com.sist.client;

import javax.swing.JDialog;

class NoticeImageAttackFinish extends JDialog{
	GameImage gimage=new GameImage();
	public NoticeImageAttackFinish(){
		add("Center", gimage);
		this.setModal(true);
		setBounds(100, 400, GameImage.attackFinishNotice.getWidth(gimage), GameImage.attackFinishNotice.getHeight(gimage));
		setVisible(false);
	}
}