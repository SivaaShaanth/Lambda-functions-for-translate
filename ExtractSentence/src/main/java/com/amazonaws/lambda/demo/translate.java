package com.amazonaws.lambda.demo;

public class translate {
private String sourceText;
private String targetText;
private String translated;
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
