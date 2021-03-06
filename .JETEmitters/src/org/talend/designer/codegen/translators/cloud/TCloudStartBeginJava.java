package org.talend.designer.codegen.translators.cloud;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.Map;
import java.util.List;

public class TCloudStartBeginJava
{
  protected static String nl;
  public static synchronized TCloudStartBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCloudStartBeginJava result = new TCloudStartBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\tif(true) {" + NL + "\t\t\tthrow new Exception(\"The instance count must be a numeric.\");" + NL + "\t\t}";
  protected final String TEXT_3 = NL + NL + "\torg.jclouds.compute.ComputeServiceContext context_";
  protected final String TEXT_4 = " = new org.jclouds.compute.ComputeServiceContextFactory().createContext(\"";
  protected final String TEXT_5 = "\", ";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = ");" + NL + "\torg.jclouds.compute.ComputeService client_";
  protected final String TEXT_8 = " = context_";
  protected final String TEXT_9 = ".getComputeService();" + NL + "\t";
  protected final String TEXT_10 = NL + "\t\torg.jclouds.ec2.services.KeyPairClient keyPairClient_";
  protected final String TEXT_11 = " = org.jclouds.ec2.EC2Client.class.cast(context_";
  protected final String TEXT_12 = ".getProviderSpecificContext().getApi()).getKeyPairServices();" + NL + "\t\torg.jclouds.ec2.domain.KeyPair result_";
  protected final String TEXT_13 = " = keyPairClient_";
  protected final String TEXT_14 = ".createKeyPairInRegion(";
  protected final String TEXT_15 = ", ";
  protected final String TEXT_16 = ");" + NL + "\t\tjava.io.FileWriter fstream_";
  protected final String TEXT_17 = " = new java.io.FileWriter(";
  protected final String TEXT_18 = " + \"/\" + ";
  protected final String TEXT_19 = " +\".pem\");" + NL + "\t\tjava.io.BufferedWriter out_";
  protected final String TEXT_20 = " = new java.io.BufferedWriter(fstream_";
  protected final String TEXT_21 = ");" + NL + "\t\tout_";
  protected final String TEXT_22 = ".write(result_";
  protected final String TEXT_23 = ".getKeyMaterial());" + NL + "\t\tout_";
  protected final String TEXT_24 = ".close();";
  protected final String TEXT_25 = NL + "\t\torg.jclouds.aws.ec2.compute.AWSEC2TemplateOptions options_";
  protected final String TEXT_26 = " = new org.jclouds.aws.ec2.compute.AWSEC2TemplateOptions();";
  protected final String TEXT_27 = NL + "\t\toptions_";
  protected final String TEXT_28 = ".overrideLoginPrivateKey(result_";
  protected final String TEXT_29 = ".getKeyMaterial());";
  protected final String TEXT_30 = NL + "\toptions_";
  protected final String TEXT_31 = ".as(";
  protected final String TEXT_32 = ".class)" + NL + "\t.";
  protected final String TEXT_33 = NL + "\t";
  protected final String TEXT_34 = NL + "\t\t\t\t.mapNewVolumeToDeviceName(";
  protected final String TEXT_35 = ", ";
  protected final String TEXT_36 = ", ";
  protected final String TEXT_37 = ")";
  protected final String TEXT_38 = NL + "\t\t\t\t.mapEBSSnapshotToDeviceName(";
  protected final String TEXT_39 = ", ";
  protected final String TEXT_40 = ", ";
  protected final String TEXT_41 = ", ";
  protected final String TEXT_42 = ")";
  protected final String TEXT_43 = NL + "\t\t\t\t.mapEphemeralDeviceToDeviceName(";
  protected final String TEXT_44 = ", ";
  protected final String TEXT_45 = ")";
  protected final String TEXT_46 = NL + "\t;" + NL + "\t" + NL + "\torg.jclouds.compute.domain.Template template_";
  protected final String TEXT_47 = " = context_";
  protected final String TEXT_48 = ".getComputeService().templateBuilder()" + NL + "\t\t.imageId(";
  protected final String TEXT_49 = " +\"/\" + ";
  protected final String TEXT_50 = ")" + NL + "\t\t.hardwareId(org.jclouds.ec2.domain.InstanceType.";
  protected final String TEXT_51 = ")" + NL + "\t\t.locationId(";
  protected final String TEXT_52 = ")" + NL + "\t\t.options(options_";
  protected final String TEXT_53 = ").build();" + NL + "\t" + NL + "\tjava.util.Set<? extends org.jclouds.compute.domain.NodeMetadata> nodes_";
  protected final String TEXT_54 = " = context_";
  protected final String TEXT_55 = ".getComputeService().createNodesInGroup(";
  protected final String TEXT_56 = ".toLowerCase(), ";
  protected final String TEXT_57 = ", template_";
  protected final String TEXT_58 = ");" + NL + "\t" + NL + "\tglobalMap.put(\"";
  protected final String TEXT_59 = "_NODE_GROUP\", ";
  protected final String TEXT_60 = ".toLowerCase());" + NL + "\tglobalMap.put(\"";
  protected final String TEXT_61 = "_NODES\", nodes_";
  protected final String TEXT_62 = ");";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String accesskey = ElementParameterParser.getValue(node, "__ACCESS_KEY__");
	String secretkey = ElementParameterParser.getValue(node, "__SECRET_KEY__");
	String provider = ElementParameterParser.getValue(node, "__PROVIDER__");
	String imageId = ElementParameterParser.getValue(node, "__IMAGE_ID__");
	String region = ElementParameterParser.getValue(node, "__REGION__");
	String zone = ElementParameterParser.getValue(node, "__ZONE__");
	int instanceCount = 0;
	String instanceName = ElementParameterParser.getValue(node, "__INSTANCE_NAME__");
	String type = ElementParameterParser.getValue(node, "__TYPE__");
	boolean proceedWithKeyPair = "true".equals(ElementParameterParser.getValue(node, "__PROCEED_WITH_KEYPAIR__"));
	String keypairOption = ElementParameterParser.getValue(node, "__KEYPAIR_OPTION__");
	String keypair = ElementParameterParser.getValue(node, "__KEYPAIR__");
	String keypairFolder = ElementParameterParser.getValue(node, "__KEYPAIR_FOLDER__");
	List<Map<String, String>> securityGroups = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__SECURITY_GROUPS__");
	List<Map<String, String>> volumes = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__VOLUMES__");
	
	String providerId = "";
	if("AWS_EC2".equals(provider)) {
		providerId = "aws-ec2";
	}
	
	String securityGroupsString = null;
	if(securityGroups.size() > 0){
		boolean isFirstGroup = true;
		for(Map<String, String> item : securityGroups){
			if(isFirstGroup) {
				isFirstGroup = false;
				securityGroupsString = item.get("GROUP");
			} else {
				securityGroupsString += " ," + item.get("GROUP");
			}
		} 
	}
	
	try {
		instanceCount = Integer.parseInt(ElementParameterParser.getValue(node, "__INSTANCE_COUNT__"));
	} catch (Exception e) {
		

    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(providerId);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(accesskey);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(secretkey);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
	if(proceedWithKeyPair && "CREATE_KEYPAIR".equals(keypairOption)) {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(region);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(keypair);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(keypairFolder);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(keypair);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
	}

	if("AWS_EC2".equals(provider)) {

    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    
	}
	if(proceedWithKeyPair && "CREATE_KEYPAIR".equals(keypairOption)) {

    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
	}

    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append("AWS_EC2".equals(provider)?"org.jclouds.aws.ec2.compute.AWSEC2TemplateOptions":"");
    stringBuffer.append(TEXT_32);
    stringBuffer.append((proceedWithKeyPair?("USE_EXISTING".equals(keypairOption)?"keyPair("+keypair+")":"keyPair(result_"+cid+".getKeyName())"):"noKeyPair()"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(securityGroupsString!=null?".securityGroups("+securityGroupsString+")":"");
    
	for(Map<String, String> volume : volumes) { //1
			String volume_type = volume.get("VOLUME_TYPE");
			if("ROOT".equals(volume_type)) {

    stringBuffer.append(TEXT_34);
    stringBuffer.append(volume.get("DEVICE"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(volume.get("SIZE"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append("true".equals(volume.get("DELETE_ON_TERMINATION")));
    stringBuffer.append(TEXT_37);
    
			} else if("EBS".equals(volume_type)) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(volume.get("DEVICE"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(volume.get("SNAPSHOT_ID"));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(volume.get("SIZE"));
    stringBuffer.append(TEXT_41);
    stringBuffer.append("true".equals(volume.get("DELETE_ON_TERMINATION")));
    stringBuffer.append(TEXT_42);
    
			} else if("EPHEMERAL".equals(volume_type)) {

    stringBuffer.append(TEXT_43);
    stringBuffer.append(volume.get("DEVICE"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(volume.get("VIRTUAL_NAME"));
    stringBuffer.append(TEXT_45);
    
			}
	}

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(region);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(imageId);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(zone);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(instanceName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(instanceCount);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_59);
    stringBuffer.append(instanceName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    return stringBuffer.toString();
  }
}
