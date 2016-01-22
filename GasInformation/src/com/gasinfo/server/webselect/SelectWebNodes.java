
package com.gasinfo.server.webselect;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import com.gasinfo.model.SelectModel;
import com.gasinfo.server.heritrix.AddWebsite;

import com.gasinfo.config.Configuration;

public class SelectWebNodes {

	private Document doc;

	public static void main(String args[]) throws Exception {
		SelectWebNodes selectwebnodes = new SelectWebNodes();
		SelectModel S = selectwebnodes.GetWebNodes(1);

	}

	// 生成基本节点
	public void CreateNodes(SelectModel selectmodel) throws Exception {
		this.OpenXml("WebSelectXml.xml");
		Element RootElemtent = doc.getRootElement();
		Element AllWebsiteElemtent = RootElemtent.element("AllWebsite");
		Element WebElemtent = RootElemtent.element("Web");
		Element WebNumberElemtent = AllWebsiteElemtent.element("WebNumber");

		if (selectmodel.getWebID().equals("-1")) {
			int intWebNumber = Integer.parseInt(WebNumberElemtent.getText()) + 1;
			selectmodel.setWebID(intWebNumber + "");
			this.CreateWebsiteNodes(0, "-1");
			this.saveXMLFile("WebSelectXml.xml");
		} else {
			Element WebsiteElemtent = WebElemtent.elementByID(selectmodel
					.getWebID());
			WebsiteElemtent.setAttributeValue("name", selectmodel.getWebName());
			List<Element> elemtents = WebsiteElemtent.elements();
			for (int i = 0; i < elemtents.size(); i++) {
				WebsiteElemtent.remove(elemtents.get(i));
			}
			this.saveXMLFile("WebSelectXml.xml");
			this.CreateWebsiteNodes(1, selectmodel.getWebID());
		}
		
	}

	// 增加或者修改节点

	public void AddOrChangeWebNodes(SelectModel selectmodel) throws Exception {
		this.OpenXml("WebSelectXml.xml");
		this.CreateNodes(selectmodel);
		Element RootElemtent = doc.getRootElement();
		Element AllWebsiteElemtent = RootElemtent.element("AllWebsite");
		Element WebElemtent = RootElemtent.element("Web");
		Element WebNumberElemtent = AllWebsiteElemtent.element("WebNumber");
		// 修改WebMessage的子节点节点
		Element WebMessageElemtent = AllWebsiteElemtent.element("WebMessage");
		Element Website = WebMessageElemtent.elementByID(selectmodel.getWebID()
				.toString());
		Website.setAttributeValue("name", selectmodel.getWebName());
		// 修改WebSite节点
		Element WebSiteElemtent = WebElemtent.elementByID(selectmodel
				.getWebID().toString());
		WebSiteElemtent.setAttributeValue("name", selectmodel.getWebName());
		// 修改WebSite下的PrePostion节点
		WebSiteElemtent.element("PrePostion").setText(
				selectmodel.getPrePostion());
		// 修改WebSite下的webWeight节点
		WebSiteElemtent.element("webWeight").setText(selectmodel.getWebWeight());
		// 修改WebSite下的seed节点
		WebSiteElemtent.element("seed").setText(selectmodel.getSeed());
		// 修改WebSite下的Module节点
		Element ModuleElemtent = WebSiteElemtent.element("Module");
		ModuleElemtent.setAttributeValue("ID", selectmodel.getModuleID());
		// 修改Module下的Mod节点
		Element ModElemtent = ModuleElemtent.elementByID(selectmodel
				.getModuleID());

		if (selectmodel.getModuleID().equals("00")) {

			ModElemtent.setAttributeValue("MK", selectmodel.getModuleA_MK());
		}
		if (selectmodel.getModuleID().equals("01")) {
			List elements = ModElemtent.elements();
			for (int i = 0; i < elements.size(); i++) {
				ModElemtent.remove((Element) elements.get(i));
			}
			ModElemtent.setAttributeValue("ClassName",
					selectmodel.getModuleB_ClassName());
			for (int i = 0; i < selectmodel.getModuleB_String().size(); i++) {
				Element StringElemtent = ModElemtent.addElement("String");
				StringElemtent.addAttribute("Postion", selectmodel
						.getModuleB_String().get(i).get("Postion"));
				StringElemtent.addAttribute("MK", selectmodel
						.getModuleB_String().get(i).get("MK"));
			}
		}
		if (selectmodel.getModuleID().equals("02")) {
			List elements = ModElemtent.elements();
			for (int i = 0; i < elements.size(); i++) {
				ModElemtent.remove((Element) elements.get(i));
			}
			ModElemtent.setAttributeValue("Var", selectmodel.getModuleC_Var());
			ModElemtent.setAttributeValue("Value",
					selectmodel.getModuleC_Value());
			for (int i = 0; i < selectmodel.getModuleC_String().size(); i++) {
				Element StringElemtent = ModElemtent.addElement("String");
				StringElemtent.addAttribute("Postion", selectmodel
						.getModuleC_String().get(i).get("Postion"));
				StringElemtent.addAttribute("MK", selectmodel
						.getModuleC_String().get(i).get("MK"));
			}
		}
		// 修改WebSite的Select节点
		Element SelectElement = WebSiteElemtent.element("Select");
		// 修改 Select的SelectTitle节点
		Element SelectTitleElement = SelectElement.element("SelectTitle");
		SelectTitleElement.setAttributeValue("ID",
				selectmodel.getSelectTitleID());
		// 修改Select的SelectTime节点
		Element SelectTimeElement = SelectElement.element("SelectTime");
		SelectTimeElement
				.setAttributeValue("ID", selectmodel.getSelectTimeID());
		// 修改SelectTime的String节点
		Element StringElement = SelectTimeElement.elementByID(selectmodel
				.getSelectTimeID());
		if (selectmodel.getSelectTimeID().equals("00")) {
			StringElement.setAttributeValue("ClassName",
					selectmodel.getSelectTimeA_ClassName());
		}

		if (selectmodel.getSelectTimeID().equals("01")) {
			StringElement.setAttributeValue("TagName",
					selectmodel.getSelectTimeB_TagName());
		}
		if (selectmodel.getSelectTimeID().equals("02")) {
			StringElement.setAttributeValue("Var",
					selectmodel.getSelectTimeC_Var());
			StringElement.setAttributeValue("Value",
					selectmodel.getSelectTimeC_Value());
		}
		// 修改Select的SelectSource节点
		Element SelectSourceElement = SelectElement.element("SelectSource");
		SelectSourceElement.setAttributeValue("ID",
				selectmodel.getSelectSourceID());
		// 修改SelectSource的String节点
		StringElement = SelectSourceElement.elementByID(selectmodel
				.getSelectSourceID());
		if (selectmodel.getSelectSourceID().equals("00")) {
			StringElement.setAttributeValue("ClassName",
					selectmodel.getSelectSourceA_ClassName());
		}

		if (selectmodel.getSelectSourceID().equals("01")) {
			StringElement.setAttributeValue("TagName",
					selectmodel.getSelectSourceB_TagName());
			StringElement.setAttributeValue("Var",
					selectmodel.getSelectSourceB_Var());
		}
		if (selectmodel.getSelectSourceID().equals("02")) {
			StringElement.setAttributeValue("Var",
					selectmodel.getSelectSourceC_Var());
			StringElement.setAttributeValue("Value",
					selectmodel.getSelectSourceC_Value());
		}
		// 修改Select的SelectContent节点
		Element SelectContentElement = SelectElement.element("SelectContent");
		SelectContentElement.setAttributeValue("ID",
				selectmodel.getSelectContentID());
		// 修改SelectContent的String节点
		StringElement = SelectContentElement.elementByID(selectmodel
				.getSelectContentID());
		if (selectmodel.getSelectContentID().equals("00")) {
			StringElement.setAttributeValue("ClassName",
					selectmodel.getSelectContentA_ClassName());
		}

		if (selectmodel.getSelectContentID().equals("01")) {
			StringElement.setAttributeValue("TagName",
					selectmodel.getSelectContentB_TagName());
		}
		if (selectmodel.getSelectContentID().equals("02")) {
			StringElement.setAttributeValue("Var",
					selectmodel.getSelectContentC_Var());
			StringElement.setAttributeValue("Value",
					selectmodel.getSelectContentC_Value());
		}
		this.saveXMLFile("WebSelectXml.xml");
	}

	// 创建一个新的节点
	public void CreateWebsiteNodes(int type, String WebID) throws Exception {
		this.OpenXml("WebSelectXml.xml");
		Element RootElemtent = doc.getRootElement();
		Element WebElemtent = RootElemtent.element("Web");
		Element AllWebsiteElemtent = RootElemtent.element("AllWebsite");
		Element WebSiteElemtent;
		if (type == 0) {
			// 修改AllWebsite节点的WebNumber节点
			Element WebNumberElemtent = AllWebsiteElemtent.element("WebNumber");
			int intWebNumber = Integer.parseInt(WebNumberElemtent.getText()) + 1;
			WebNumberElemtent.setText(String.valueOf(intWebNumber));
			// 增加WebMessage的子节点节点
			Element WebMessageElemtent = AllWebsiteElemtent
					.element("WebMessage");
			Element Website = WebMessageElemtent.addElement("Website");
			Website.setAttributeValue("name", "");
			Website.setAttributeValue("ID", String.valueOf(intWebNumber));
			// 增加WebSite节点
			WebSiteElemtent = WebElemtent.addElement("Website");
			WebSiteElemtent.setAttributeValue("name", "");
			WebSiteElemtent.setAttributeValue("ID",
					String.valueOf(intWebNumber));
		} else {
			WebSiteElemtent = WebElemtent.elementByID(WebID);
		}

		// 增加WebSite下的PrePostion节点
		Element PrePostionElemtent = WebSiteElemtent.addElement("PrePostion");
		// 增加WebSite下的webWeight节点
		Element webWeightElemtent = WebSiteElemtent.addElement("webWeight");
		// 增加WebSite下的seed节点
		Element seedElemtent = WebSiteElemtent.addElement("seed");
		// 增加WebSite下的Module节点
		Element ModuleElemtent = WebSiteElemtent.addElement("Module");
		ModuleElemtent.setAttributeValue("ID", "");
		// 增加Module下的Mod节点
		Element ModElemtent = ModuleElemtent.addElement("Mod");
		ModElemtent.addAttribute("ID", "00");
		ModElemtent.addAttribute("MK", "");
		ModElemtent = ModuleElemtent.addElement("Mod");
		ModElemtent.addAttribute("ID", "01");
		ModElemtent.addAttribute("ClassName", "");
		ModElemtent = ModuleElemtent.addElement("Mod");
		ModElemtent.addAttribute("ID", "02");
		ModElemtent.addAttribute("Var", "");
		ModElemtent.addAttribute("Value", "");
		// 增加 WebSite的Select节点
		Element SelectElement = WebSiteElemtent.addElement("Select");
		// 增加 Select的SelectTitle节点
		Element SelectTitleElement = SelectElement.addElement("SelectTitle");
		SelectTitleElement.addAttribute("ID", "");
		// 增加 Select的SelectTime节点
		Element SelectTimeElement = SelectElement.addElement("SelectTime");
		SelectTimeElement.addAttribute("ID", "");
		// 增加 SelectTime的String节点
		Element StringElement = SelectTimeElement.addElement("String");
		StringElement.addAttribute("ID", "00");
		StringElement.addAttribute("ClassName", "");
		StringElement = SelectTimeElement.addElement("String");
		StringElement.addAttribute("ID", "01");
		StringElement.addAttribute("TagName", "");
		StringElement = SelectTimeElement.addElement("String");
		StringElement.addAttribute("ID", "02");
		StringElement.addAttribute("Var", "");
		StringElement.addAttribute("Value", "");
		// 增加 Select的SelectSource节点
		Element SelectSourceElement = SelectElement.addElement("SelectSource");
		SelectSourceElement.addAttribute("ID", "");
		// 增加 SelectSource的String节点
		StringElement = SelectSourceElement.addElement("String");
		StringElement.addAttribute("ID", "00");
		StringElement.addAttribute("ClassName", "");
		StringElement = SelectSourceElement.addElement("String");
		StringElement.addAttribute("ID", "01");
		StringElement.addAttribute("TagName", "");
		StringElement.addAttribute("Var", "");
		StringElement = SelectSourceElement.addElement("String");
		StringElement.addAttribute("ID", "02");
		StringElement.addAttribute("Var", "");
		StringElement.addAttribute("Value", "");
		// 增加 Select的SelectContent节点
		Element SelectContentElement = SelectElement
				.addElement("SelectContent");
		SelectContentElement.addAttribute("ID", "");
		// 增加 SelectContent的String节点
		StringElement = SelectContentElement.addElement("String");
		StringElement.addAttribute("ID", "00");
		StringElement.addAttribute("ClassName", "");
		StringElement = SelectContentElement.addElement("String");
		StringElement.addAttribute("ID", "01");
		StringElement.addAttribute("TagName", "");
		StringElement = SelectContentElement.addElement("String");
		StringElement.addAttribute("ID", "02");
		StringElement.addAttribute("Var", "");
		StringElement.addAttribute("Value", "");

		this.saveXMLFile("WebSelectXml.xml");
	}

	// 得到所有网站信息
	public ArrayList<HashMap<String, String>> GetAllWebs() throws Exception {
		ArrayList<HashMap<String, String>> listAllWebs = new ArrayList<HashMap<String, String>>();
		this.OpenXml("WebSelectXml.xml");
		Element RootElemtent = doc.getRootElement();
		Element AllWebsiteElemtent = RootElemtent.element("AllWebsite");
		Element WebMessageElemtent = AllWebsiteElemtent.element("WebMessage");
		List<Element> elemtents = WebMessageElemtent.elements();
		for (int i = 0; i < elemtents.size(); i++) {
			HashMap<String, String> hashmap = new HashMap<String, String>();
			hashmap.put("name", elemtents.get(i).attributeValue("name"));
			hashmap.put("ID", elemtents.get(i).attributeValue("ID"));
			listAllWebs.add(hashmap);
		}
		return listAllWebs;

	}

	// 得到网站的节点信息
	public SelectModel GetWebNodes(int WebID) throws Exception {
		SelectModel selectmodel = new SelectModel();
		selectmodel.setWebID(WebID + "");
		this.OpenXml("WebSelectXml.xml");
		Element RootElemtent = doc.getRootElement();
		Element WebElemtent = RootElemtent.element("Web");
		// 找到WebSite节点
		Element WebSiteElemtent = WebElemtent.elementByID(selectmodel
				.getWebID().toString());
		// 返回WebSiteElemtent的name属性
		selectmodel.setWebName(WebSiteElemtent.attributeValue("name"));
		// 返回原始路径
		selectmodel.setPrePostion(WebSiteElemtent.element("PrePostion")
				.getTextTrim());
		// 返回权重
		selectmodel.setWebWeight(WebSiteElemtent.element("webWeight")
				.getTextTrim());
		// 返回seed
		selectmodel.setSeed(WebSiteElemtent.element("seed").getTextTrim());
		// 找到Module节点
		Element ModuleElemtent = WebSiteElemtent.element("Module");
		// 返回ModuleID
		selectmodel.setModuleID(ModuleElemtent.attributeValue("ID"));
		// 找到Mod节点
		Element ModElemtent = ModuleElemtent.elementByID(selectmodel
				.getModuleID());
		if (selectmodel.getModuleID().equals("00")) {
			// 返回Module_A的MK
			selectmodel.setModuleA_MK(ModElemtent.attributeValue("MK"));
		}
		if (selectmodel.getModuleID().equals("01")) {
			// 返回Module_B的ClassName
			selectmodel.setModuleB_ClassName(ModElemtent
					.attributeValue("ClassName"));
			List<Element> StringElemtents = ModElemtent.elements();
			ArrayList<HashMap<String, String>> listModuleB_String = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < StringElemtents.size(); i++) {
				HashMap<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("Postion",
						StringElemtents.get(i).attributeValue("Postion"));
				hashmap.put("MK", StringElemtents.get(i).attributeValue("MK"));
				listModuleB_String.add(hashmap);
			}
			// 返回Module_B的String
			selectmodel.setModuleB_String(listModuleB_String);
		}
		if (selectmodel.getModuleID().equals("02")) {
			// 返回Module_C的Var
			selectmodel.setModuleC_Var(ModElemtent.attributeValue("Var"));
			// 返回Module_C的Value
			selectmodel.setModuleC_Value(ModElemtent.attributeValue("Value"));
			List<Element> StringElemtents = ModElemtent.elements();
			ArrayList<HashMap<String, String>> listModuleC_String = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < StringElemtents.size(); i++) {
				HashMap<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("Postion",
						StringElemtents.get(i).attributeValue("Postion"));
				hashmap.put("MK", StringElemtents.get(i).attributeValue("MK"));
				listModuleC_String.add(hashmap);
			}
			// 返回Module_C的String
			selectmodel.setModuleC_String(listModuleC_String);

		}

		// 找到WebSite的Select节点
		Element SelectElement = WebSiteElemtent.element("Select");
		// 找到Select的SelectTitle节点
		Element SelectTitleElement = SelectElement.element("SelectTitle");
		// 返回SelectTitleID
		selectmodel.setSelectTitleID(SelectTitleElement.attributeValue("ID"));
		// 找到Select的SelectTime节点
		Element SelectTimeElement = SelectElement.element("SelectTime");
		// 返回SelectTimeID
		selectmodel.setSelectTimeID(SelectTimeElement.attributeValue("ID"));
		// 找到SelectTime的String节点
		Element StringElement = SelectTimeElement.elementByID(selectmodel
				.getSelectTimeID());
		if (selectmodel.getSelectTimeID().equals("00")) {
			// 返回SelectTime_A的ClassName
			selectmodel.setSelectTimeA_ClassName(StringElement
					.attributeValue("ClassName"));
		}

		if (selectmodel.getSelectTimeID().equals("01")) {
			// 返回SelectTime_B的TagName
			selectmodel.setSelectTimeB_TagName(StringElement
					.attributeValue("TagName"));
		}
		if (selectmodel.getSelectTimeID().equals("02")) {
			// 返回SelectTime_C的Var
			selectmodel.setSelectTimeC_Var(StringElement.attributeValue("Var"));
			// 返回SelectTime_C的Value
			selectmodel.setSelectTimeC_Value(StringElement
					.attributeValue("Value"));
		}
		// 找到Select的SelectSource节点
		Element SelectSourceElement = SelectElement.element("SelectSource");
		// 返回SelectSourceID
		selectmodel.setSelectSourceID(SelectSourceElement.attributeValue("ID"));
		// 找到SelectSource的String节点
		StringElement = SelectSourceElement.elementByID(selectmodel
				.getSelectSourceID());
		if (selectmodel.getSelectSourceID().equals("00")) {
			// 返回SelectSource_A的ClassName
			selectmodel.setSelectSourceA_ClassName(StringElement
					.attributeValue("ClassName"));
		}

		if (selectmodel.getSelectSourceID().equals("01")) {
			// 返回SelectSource_B的TagName
			selectmodel.setSelectSourceB_TagName(StringElement
					.attributeValue("TagName"));
			// 返回SelectSource_B的Var
			selectmodel.setSelectSourceB_Var(StringElement
					.attributeValue("Var"));
		}
		if (selectmodel.getSelectSourceID().equals("02")) {
			// 返回SelectSource_C的Var
			selectmodel.setSelectSourceC_Var(StringElement
					.attributeValue("Var"));
			// 返回SelectSource_C的Value
			selectmodel.setSelectSourceC_Value(StringElement
					.attributeValue("Value"));
		}
		// 找到Select的SelectContent节点
		Element SelectContentElement = SelectElement.element("SelectContent");
		// 返回SelectContentID
		selectmodel.setSelectContentID(SelectContentElement
				.attributeValue("ID"));
		// 找到SelectContent的String节点
		StringElement = SelectContentElement.elementByID(selectmodel
				.getSelectContentID());
		if (selectmodel.getSelectContentID().equals("00")) {
			// 返回SelectContent_A的ClassName
			selectmodel.setSelectContentA_ClassName(StringElement
					.attributeValue("ClassName"));
		}

		if (selectmodel.getSelectContentID().equals("01")) {
			// 返回SelectContent_B的TagName
			selectmodel.setSelectContentB_TagName(StringElement
					.attributeValue("TagName"));
		}
		if (selectmodel.getSelectContentID().equals("02")) {
			// 返回SelectContent_C的Var
			selectmodel.setSelectContentC_Var(StringElement
					.attributeValue("Var"));
			// 返回SelectContent_C的Value
			selectmodel.setSelectContentC_Value(StringElement
					.attributeValue("Value"));
		}
		return selectmodel;

	}

	// 数据导入
		public String EnterXML(String Postion,ArrayList<HashMap<String, String>> listAllWebs) throws Exception {
			File file = new File(Postion);
			SAXReader sr = new SAXReader();
			sr.setEncoding("UTF-8");
			doc = sr.read(Postion);
			// // Document document = sr.read(new BufferedReader(new
			// InputStreamReader(new FileInputStream(file), "UTF-8")));
			// doc = sr.read(new BufferedReader(new InputStreamReader(new
			// FileInputStream(file), "UTF-8")));
			SelectModel selectmodel = new SelectModel();
			Element WebSiteElemtent = doc.getRootElement();
			// 给WebID赋值
			selectmodel.setWebID(WebSiteElemtent.attributeValue("ID"));
			// 给WebName赋值
			selectmodel.setWebName(WebSiteElemtent.attributeValue("name"));
			
			
			for(int i=0;i<listAllWebs.size();i++){
				if(selectmodel.getWebName().trim().equals(listAllWebs.get(i).get("name").trim())){
					return "error";
				}
			}
			
			
			
			// 返回原始路径
			selectmodel.setPrePostion(WebSiteElemtent.element("PrePostion")
					.getTextTrim());
			// 返回权重
			selectmodel.setWebWeight(WebSiteElemtent.element("webWeight")
					.getTextTrim());
			// 返回seed
			selectmodel.setSeed(WebSiteElemtent.element("seed").getTextTrim());
			// 找到Module节点
			Element ModuleElemtent = WebSiteElemtent.element("Module");
			// 返回ModuleID
			selectmodel.setModuleID(ModuleElemtent.attributeValue("ID"));
			// 找到Mod节点
			Element ModElemtent = ModuleElemtent.elementByID(selectmodel
					.getModuleID());
			if (selectmodel.getModuleID().equals("00")) {
				// 返回Module_A的MK
				selectmodel.setModuleA_MK(ModElemtent.attributeValue("MK"));
				
				
				
				
				String numModuleId = StrModuleIdtoNumModuleId(ModElemtent.attributeValue("MK"));
				AddWebsite aw = new AddWebsite();
				aw.addWeb(WebSiteElemtent.attributeValue("name"), WebSiteElemtent.element("seed").getTextTrim(),numModuleId);
			
			
			}
			if (selectmodel.getModuleID().equals("01")) {
				// 返回Module_B的ClassName
				selectmodel.setModuleB_ClassName(ModElemtent
						.attributeValue("ClassName"));
				List<Element> StringElemtents = ModElemtent.elements();
				ArrayList<HashMap<String, String>> listModuleB_String = new ArrayList<HashMap<String, String>>();
				//模块数组
				String[] moduleIds = new String[StringElemtents.size()];
				
				for (int i = 0; i < StringElemtents.size(); i++) {
					HashMap<String, String> hashmap = new HashMap<String, String>();
					hashmap.put("Postion",
							StringElemtents.get(i).attributeValue("Postion"));
					hashmap.put("MK", StringElemtents.get(i).attributeValue("MK"));
					
					moduleIds[i] = StrModuleIdtoNumModuleId(StringElemtents.get(i).attributeValue("MK"));
					
					listModuleB_String.add(hashmap);
				}
				
				
				// 返回Module_B的String
				selectmodel.setModuleB_String(listModuleB_String);
				
				
//				String numModuleId = StrModuleIdtoNumModuleId(ModElemtent.attributeValue("MK"));
				AddWebsite aw = new AddWebsite();
				aw.addWeb(WebSiteElemtent.attributeValue("name"), WebSiteElemtent.element("seed").getTextTrim(),moduleIds);
			}
			
			
			
			
			if (selectmodel.getModuleID().equals("02")) {
				// 返回Module_C的Var
				selectmodel.setModuleC_Var(ModElemtent.attributeValue("Var"));
				// 返回Module_C的Value
				selectmodel.setModuleC_Value(ModElemtent.attributeValue("Value"));
				List<Element> StringElemtents = ModElemtent.elements();
				ArrayList<HashMap<String, String>> listModuleC_String = new ArrayList<HashMap<String, String>>();
				
				String[] moduleIds = new String[StringElemtents.size()];
				
				for (int i = 0; i < StringElemtents.size(); i++) {
					HashMap<String, String> hashmap = new HashMap<String, String>();
					hashmap.put("Postion",
							StringElemtents.get(i).attributeValue("Postion"));
					hashmap.put("MK", StringElemtents.get(i).attributeValue("MK"));
					moduleIds[i] = StrModuleIdtoNumModuleId(StringElemtents.get(i).attributeValue("MK"));
					listModuleC_String.add(hashmap);
				}
				// 返回Module_C的String
				selectmodel.setModuleC_String(listModuleC_String);
				
//				String numModuleId = StrModuleIdtoNumModuleId(ModElemtent.attributeValue("MK"));
				AddWebsite aw = new AddWebsite();
				aw.addWeb(WebSiteElemtent.attributeValue("name"), WebSiteElemtent.element("seed").getTextTrim(),moduleIds);
			
				

			}

			// 找到WebSite的Select节点
			Element SelectElement = WebSiteElemtent.element("Select");
			// 找到Select的SelectTitle节点
			Element SelectTitleElement = SelectElement.element("SelectTitle");
			// 返回SelectTitleID
			selectmodel.setSelectTitleID(SelectTitleElement.attributeValue("ID"));
			// 找到Select的SelectTime节点
			Element SelectTimeElement = SelectElement.element("SelectTime");
			// 返回SelectTimeID
			selectmodel.setSelectTimeID(SelectTimeElement.attributeValue("ID"));
			// 找到SelectTime的String节点
			Element StringElement = SelectTimeElement.elementByID(selectmodel
					.getSelectTimeID());
			if (selectmodel.getSelectTimeID().equals("00")) {
				// 返回SelectTime_A的ClassName
				selectmodel.setSelectTimeA_ClassName(StringElement
						.attributeValue("ClassName"));
			}

			if (selectmodel.getSelectTimeID().equals("01")) {
				// 返回SelectTime_B的TagName
				selectmodel.setSelectTimeB_TagName(StringElement
						.attributeValue("TagName"));
			}
			if (selectmodel.getSelectTimeID().equals("02")) {
				// 返回SelectTime_C的Var
				selectmodel.setSelectTimeC_Var(StringElement.attributeValue("Var"));
				// 返回SelectTime_C的Value
				selectmodel.setSelectTimeC_Value(StringElement
						.attributeValue("Value"));
			}
			// 找到Select的SelectSource节点
			Element SelectSourceElement = SelectElement.element("SelectSource");
			// 返回SelectSourceID
			selectmodel.setSelectSourceID(SelectSourceElement.attributeValue("ID"));
			// 找到SelectSource的String节点
			StringElement = SelectSourceElement.elementByID(selectmodel
					.getSelectSourceID());
			if (selectmodel.getSelectSourceID().equals("00")) {
				// 返回SelectSource_A的ClassName
				selectmodel.setSelectSourceA_ClassName(StringElement
						.attributeValue("ClassName"));
			}

			if (selectmodel.getSelectSourceID().equals("01")) {
				// 返回SelectSource_B的TagName
				selectmodel.setSelectSourceB_TagName(StringElement
						.attributeValue("TagName"));
				// 返回SelectSource_B的Var
				selectmodel.setSelectSourceB_Var(StringElement
						.attributeValue("Var"));
			}
			if (selectmodel.getSelectSourceID().equals("02")) {
				// 返回SelectSource_C的Var
				selectmodel.setSelectSourceC_Var(StringElement
						.attributeValue("Var"));
				// 返回SelectSource_C的Value
				selectmodel.setSelectSourceC_Value(StringElement
						.attributeValue("Value"));
			}
			// 找到Select的SelectContent节点
			Element SelectContentElement = SelectElement.element("SelectContent");
			// 返回SelectContentID
			selectmodel.setSelectContentID(SelectContentElement
					.attributeValue("ID"));
			// 找到SelectContent的String节点
			StringElement = SelectContentElement.elementByID(selectmodel
					.getSelectContentID());
			if (selectmodel.getSelectContentID().equals("00")) {
				// 返回SelectContent_A的ClassName
				selectmodel.setSelectContentA_ClassName(StringElement
						.attributeValue("ClassName"));
			}

			if (selectmodel.getSelectContentID().equals("01")) {
				// 返回SelectContent_B的TagName
				selectmodel.setSelectContentB_TagName(StringElement
						.attributeValue("TagName"));
			}
			if (selectmodel.getSelectContentID().equals("02")) {
				// 返回SelectContent_C的Var
				selectmodel.setSelectContentC_Var(StringElement
						.attributeValue("Var"));
				// 返回SelectContent_C的Value
				selectmodel.setSelectContentC_Value(StringElement
						.attributeValue("Value"));
			}

			this.AddOrChangeWebNodes(selectmodel);
			return "sucess";

		}
	// 读取XML
	public void OpenXml(String xml) throws Exception {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")
				+ File.separator + xml;
		SAXReader sr = new SAXReader();
		sr.setEncoding("UTF-8");
		doc = sr.read(RootURL_WebSelectXml);
	}

	// 存储XML
	private void saveXMLFile(String xml) {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")
				+ File.separator + xml;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(new File(
					RootURL_WebSelectXml)), format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			// String loginfo = StackTraceToString.getExceptionTrace(e);
			// writelog.writeLogToEnd("LocalServerManager", loginfo);
		}
	}
	
	//返回0表示传入的webid有误
	public String StrModuleIdtoNumModuleId(String WebID){
		if(WebID.equals("Gzdt")){
			return "1";
		}
		if(WebID.equals("Kcgl")){
			return "2";
		}
		if(WebID.equals("Zcfg")){
			return "3";
		}
		if(WebID.equals("Yzrd")){
			return "4";
		}
		if(WebID.equals("Gjhz")){
			return "5";
		}
		if(WebID.equals("Kjjz")){
			return "6";
		}
		if(WebID.equals("Tpxw")){
			return "7";
		}
		if(WebID.equals("Lddt")){
			return "8";
		}
		if(WebID.equals("Tjsj")){
			return "9";
		}
		
		if(WebID.equals("Ktkf")){
			return "10";
		}
		if(WebID.equals("Yjxx")){
			return "11";
		}
		//返回0表示传入的webid有误
		return "0";
	}
	
}
