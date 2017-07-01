package com.amazonaws.lambda.demo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
public class translate {
private String sourceText;
private String targetText;
private String translated;
private String mail;
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
//private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
public String getTranslated() {
	return translated;
}
public void setTranslated(String translated) {
	this.translated = translated; 
}
public String getSourceText() {
	return sourceText;
}
public void setSourceText(String sourceText) {
	this.sourceText = sourceText;
}
public String getTargetText() {
	return targetText;
}
public void setTargetText(String targetText) {
	this.targetText = targetText;
}
}
