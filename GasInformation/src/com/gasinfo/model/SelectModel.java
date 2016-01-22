package com.gasinfo.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.gasinfo.action.newsAction;

public class SelectModel {

	private String seed = "";
	private String WebName = "";
	private String WebID = "-1";
	private String webWeight="";
	private String PrePostion = "";
	private String ModuleID = "";
	private String SelectTitleID = "";
	private String SelectTimeID = "";
	private String SelectSourceID = "";
	private String SelectContentID = "";
	private String ModuleA_MK = "";
	private String ModuleB_ClassName = "";
	private ArrayList<HashMap<String, String>> ModuleB_String = new ArrayList<HashMap<String, String>>();
	private String ModuleC_Var = "";
	private String ModuleC_Value = "";
	private ArrayList<HashMap<String, String>> ModuleC_String = new ArrayList<HashMap<String, String>>();
	private String SelectTimeA_ClassName = "";
	private String SelectTimeB_TagName = "";
	private String SelectTimeC_Var = "";
	private String SelectTimeC_Value = "";
	private String SelectSourceA_ClassName = "";
	private String SelectSourceB_TagName = "";
	private String SelectSourceB_Var = "";
	private String SelectSourceC_Var = "";
	private String SelectSourceC_Value = "";
	private String SelectContentA_ClassName = "";
	private String SelectContentB_TagName = "";
	private String SelectContentC_Var = "";
	private String SelectContentC_Value = "";

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getWebWeight() {
		return webWeight;
	}

	public void setWebWeight(String webWeight) {
		this.webWeight = webWeight;
	}

	public String getWebName() {
		return WebName;
	}

	public void setWebName(String webName) {
		WebName = webName;
	}

	public String getWebID() {
		return WebID;
	}

	public void setWebID(String webID) {
		WebID = webID;
	}

	public String getPrePostion() {
		return PrePostion;
	}

	public void setPrePostion(String prePostion) {
		PrePostion = prePostion;
	}

	public String getModuleID() {
		return ModuleID;
	}

	public void setModuleID(String moduleID) {
		ModuleID = moduleID;
	}

	public String getSelectTitleID() {
		return SelectTitleID;
	}

	public void setSelectTitleID(String selectTitleID) {
		SelectTitleID = selectTitleID;
	}

	public String getSelectTimeID() {
		return SelectTimeID;
	}

	public void setSelectTimeID(String selectTimeID) {
		SelectTimeID = selectTimeID;
	}

	public String getSelectSourceID() {
		return SelectSourceID;
	}

	public void setSelectSourceID(String selectSourceID) {
		SelectSourceID = selectSourceID;
	}

	public String getSelectContentID() {
		return SelectContentID;
	}

	public void setSelectContentID(String selectContentID) {
		SelectContentID = selectContentID;
	}

	public String getModuleA_MK() {
		return ModuleA_MK;
	}

	public void setModuleA_MK(String moduleA_MK) {
		ModuleA_MK = moduleA_MK;
	}

	public String getModuleB_ClassName() {
		return ModuleB_ClassName;
	}

	public void setModuleB_ClassName(String moduleB_ClassName) {
		ModuleB_ClassName = moduleB_ClassName;
	}

	public ArrayList<HashMap<String, String>> getModuleB_String() {
		return ModuleB_String;
	}

	public void setModuleB_String(
			ArrayList<HashMap<String, String>> moduleB_String) {
		ModuleB_String = moduleB_String;
	}

	public ArrayList<HashMap<String, String>> getModuleC_String() {
		return ModuleC_String;
	}

	public void setModuleC_String(
			ArrayList<HashMap<String, String>> moduleC_String) {
		ModuleC_String = moduleC_String;
	}

	public String getModuleC_Var() {
		return ModuleC_Var;
	}

	public void setModuleC_Var(String moduleC_Var) {
		ModuleC_Var = moduleC_Var;
	}

	public String getModuleC_Value() {
		return ModuleC_Value;
	}

	public void setModuleC_Value(String moduleC_Value) {
		ModuleC_Value = moduleC_Value;
	}

	public String getSelectTimeA_ClassName() {
		return SelectTimeA_ClassName;
	}

	public void setSelectTimeA_ClassName(String selectTimeA_ClassName) {
		SelectTimeA_ClassName = selectTimeA_ClassName;
	}

	public String getSelectTimeB_TagName() {
		return SelectTimeB_TagName;
	}

	public void setSelectTimeB_TagName(String selectTimeB_TagName) {
		SelectTimeB_TagName = selectTimeB_TagName;
	}

	public String getSelectTimeC_Var() {
		return SelectTimeC_Var;
	}

	public void setSelectTimeC_Var(String selectTimeC_Var) {
		SelectTimeC_Var = selectTimeC_Var;
	}

	public String getSelectTimeC_Value() {
		return SelectTimeC_Value;
	}

	public void setSelectTimeC_Value(String selectTimeC_Value) {
		SelectTimeC_Value = selectTimeC_Value;
	}

	public String getSelectSourceA_ClassName() {
		return SelectSourceA_ClassName;
	}

	public void setSelectSourceA_ClassName(String selectSourceA_ClassName) {
		SelectSourceA_ClassName = selectSourceA_ClassName;
	}

	public String getSelectSourceB_TagName() {
		return SelectSourceB_TagName;
	}

	public void setSelectSourceB_TagName(String selectSourceB_TagName) {
		SelectSourceB_TagName = selectSourceB_TagName;
	}

	public String getSelectSourceB_Var() {
		return SelectSourceB_Var;
	}

	public void setSelectSourceB_Var(String selectSourceB_Var) {
		SelectSourceB_Var = selectSourceB_Var;
	}

	public String getSelectSourceC_Var() {
		return SelectSourceC_Var;
	}

	public void setSelectSourceC_Var(String selectSourceC_Var) {
		SelectSourceC_Var = selectSourceC_Var;
	}

	public String getSelectSourceC_Value() {
		return SelectSourceC_Value;
	}

	public void setSelectSourceC_Value(String selectSourceC_Value) {
		SelectSourceC_Value = selectSourceC_Value;
	}

	public String getSelectContentA_ClassName() {
		return SelectContentA_ClassName;
	}

	public void setSelectContentA_ClassName(String selectContentA_ClassName) {
		SelectContentA_ClassName = selectContentA_ClassName;
	}

	public String getSelectContentB_TagName() {
		return SelectContentB_TagName;
	}

	public void setSelectContentB_TagName(String selectContentB_TagName) {
		SelectContentB_TagName = selectContentB_TagName;
	}

	public String getSelectContentC_Var() {
		return SelectContentC_Var;
	}

	public void setSelectContentC_Var(String selectContentC_Var) {
		SelectContentC_Var = selectContentC_Var;
	}

	public String getSelectContentC_Value() {
		return SelectContentC_Value;
	}

	public void setSelectContentC_Value(String selectContentC_Value) {
		SelectContentC_Value = selectContentC_Value;
	}

}
