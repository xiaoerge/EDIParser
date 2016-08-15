package com.xiaoerge.healthcare.x12.benefit;

import com.xiaoerge.healthcare.x12.benefit.inquiry.BenefitInquiryDependent;
import com.xiaoerge.healthcare.x12.benefit.inquiry.BenefitInquirySubscriber;
import com.xiaoerge.healthcare.x12.benefit.inquiry.BenefitInquirySubscriberEligibility;
import com.xiaoerge.healthcare.x12.segment.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoerge on 8/12/16.
 */
public class BenefitInquirySubscriberTest {

    @Test
    public void testParseBenefitSubscriberInquiry() {
        String subscriberString = "HL*5*4*22*1~" +
                    "TRN*1*109834652831*WXYZCLEARH*REALTIME~" +
                    "NM1*IL*1*SMITH*JOHN*L***MI*444115555~" +
                    "REF*1L*660415~" +
                    "N3*15197 BROADWAY AVENUE*APT 215~" +
                    "N4*KANSAS CITY*MO*64108~" +
                    "PRV*RF*PXC*207Q00000X~" +
                    "DMG*D8*19430917*M~" +
                    "INS*Y*18***************3~" +
                    "HI*BK:8901*BF:87200*BF:5559~" +
                    "DTP*281*D8*20051015~" +
                    "DTP*291*D8*20051015~" +
                    "EQ*30**FAM~" +
                    "EQ*31**FAM~" +
                    "EQ*32**FAM~" +
                    "EQ*33**FAM~" +
                    "EQ*34**FAM~";

        String dependentString = "HL*6*5*22*0~" +
                "TRN*1*93175-012547*9877281234~" +
                "TRN*1*109834652831*WXYZCLEARH*REALTIME~" +
                "NM1*IL*1*SMITH*ROBERT****MI*11122333301~" +
                "DMG*D8*19430519~" +
                "DTP*291*D8*20060501~" +
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~";

        BenefitInquirySubscriber subscriber = new BenefitInquirySubscriber(subscriberString+dependentString);

        Assert.assertTrue(subscriber.validate());
        Assert.assertEquals(subscriberString+dependentString, subscriber.toString());
        Assert.assertEquals("HL*5*4*22*1~", subscriber.getSubscriberLevel().toString());
        Assert.assertEquals("TRN*1*109834652831*WXYZCLEARH*REALTIME~", subscriber.getSubscriberTraces().get(0).toString());
        Assert.assertEquals(1, subscriber.getSubscriberTraces().size());
        Assert.assertEquals("NM1*IL*1*SMITH*JOHN*L***MI*444115555~", subscriber.getSubscriberName().toString());
        Assert.assertEquals("REF*1L*660415~", subscriber.getAdditionalIdentification().get(0).toString());
        Assert.assertEquals("N3*15197 BROADWAY AVENUE*APT 215~", subscriber.getSubscriberAddress().toString());
        Assert.assertEquals("N4*KANSAS CITY*MO*64108~", subscriber.getSubscriberCityState().toString());
        Assert.assertEquals("PRV*RF*PXC*207Q00000X~", subscriber.getProviderInformation().toString());
        Assert.assertEquals("DMG*D8*19430917*M~", subscriber.getSubscriberDemographic().toString());
        Assert.assertEquals("INS*Y*18***************3~", subscriber.getMultipleBirthSequenceNumber().toString());
        Assert.assertEquals("HI*BK:8901*BF:87200*BF:5559~", subscriber.getSubscriberHealthCareDiagnosisCode().toString());
        Assert.assertEquals("DTP*281*D8*20051015~", subscriber.getSubscriberDate().get(0).toString());
        Assert.assertEquals("DTP*291*D8*20051015~", subscriber.getSubscriberDate().get(1).toString());
        Assert.assertEquals(2, subscriber.getSubscriberDate().size());

        for(int i = 0; i < 5; i++) {
            BenefitInquirySubscriberEligibility benefitInquirySubscriberEligibility = subscriber.getSubscriberEligibility().get(i);
            Assert.assertEquals("EQ*"+(30+i)+"**FAM~",
                    benefitInquirySubscriberEligibility.toString());
        }
        Assert.assertEquals(5, subscriber.getSubscriberEligibility().size());

        Assert.assertEquals(1, subscriber.getDependents().size());
        Assert.assertEquals(dependentString, subscriber.getDependents().get(0).toString());
        Assert.assertEquals("5", subscriber.getDependents().get(0).getDependentLevel().getHierarchicalParentIDNumber());
        Assert.assertEquals(5, subscriber.getDependents().get(0).getDependentEligibility().size());
    }

    @Test
    public void testCreateBenefitSubscriberInquiry() {
        String subscriberString = "HL*5*4*22*1~" +
                "TRN*1*109834652831*WXYZCLEARH*REALTIME~" +
                "NM1*IL*1*SMITH*JOHN*L***MI*444115555~" +
                "REF*1L*660415~" +
                "N3*15197 BROADWAY AVENUE*APT 215~" +
                "N4*KANSAS CITY*MO*64108~" +
                "PRV*RF*PXC*207Q00000X~" +
                "DMG*D8*19430917*M~" +
                "INS*Y*18***************3~" +
                "HI*BK:8901*BF:87200*BF:5559~" +
                "DTP*281*D8*20051015~" +
                "DTP*291*D8*20051015~" +
                "EQ*30**FAM~" +
                "EQ*31**FAM~" +
                "EQ*32**FAM~" +
                "EQ*33**FAM~" +
                "EQ*34**FAM~";

        String dependentString = "HL*6*5*22*0~" +
                "TRN*1*93175-012547*9877281234~" +
                "TRN*1*109834652831*WXYZCLEARH*REALTIME~" +
                "NM1*IL*1*SMITH*ROBERT****MI*11122333301~" +
                "DMG*D8*19430519~" +
                "DTP*291*D8*20060501~" +
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~"+
                "EQ*30~" +
                "III*ZZ*21~" +
                "REF*9F*660415~" +
                "DTP*291*D8*20051031~";

        BenefitInquirySubscriber subscriber = new BenefitInquirySubscriber();
        subscriber.setSubscriberLevel(new HL("HL*5*4*22*1~"));
        subscriber.getSubscriberTraces().add(new TRN("TRN*1*109834652831*WXYZCLEARH*REALTIME~"));
        subscriber.setSubscriberName(new NM1("NM1*IL*1*SMITH*JOHN*L***MI*444115555~"));
        subscriber.getAdditionalIdentification().add(new REF("REF*1L*660415~"));
        subscriber.setSubscriberAddress(new N3("N3*15197 BROADWAY AVENUE*APT 215~"));
        subscriber.setSubscriberCityState(new N4("N4*KANSAS CITY*MO*64108~"));
        subscriber.setProviderInformation(new PRV("PRV*RF*PXC*207Q00000X~"));
        subscriber.setSubscriberDemographic(new DMG("DMG*D8*19430917*M~"));
        subscriber.setMultipleBirthSequenceNumber(new INS("INS*Y*18***************3~"));
        subscriber.setSubscriberHealthCareDiagnosisCode(new HI("HI*BK:8901*BF:87200*BF:5559~"));
        subscriber.getSubscriberDate().add(new DTP("DTP*281*D8*20051015~"));
        subscriber.getSubscriberDate().add(new DTP("DTP*291*D8*20051015~"));

        for(int i = 0; i < 5; i++) {
            subscriber.getSubscriberEligibility().add(new BenefitInquirySubscriberEligibility("EQ*"+(30+i)+"**FAM~"));
        }
        subscriber.getDependents().add(new BenefitInquiryDependent(dependentString));

        Assert.assertTrue(subscriber.validate());
        Assert.assertEquals(subscriberString+dependentString, subscriber.toString());
        Assert.assertEquals("HL*5*4*22*1~", subscriber.getSubscriberLevel().toString());
        Assert.assertEquals("TRN*1*109834652831*WXYZCLEARH*REALTIME~", subscriber.getSubscriberTraces().get(0).toString());
        Assert.assertEquals(1, subscriber.getSubscriberTraces().size());
        Assert.assertEquals("NM1*IL*1*SMITH*JOHN*L***MI*444115555~", subscriber.getSubscriberName().toString());
        Assert.assertEquals("REF*1L*660415~", subscriber.getAdditionalIdentification().get(0).toString());
        Assert.assertEquals("N3*15197 BROADWAY AVENUE*APT 215~", subscriber.getSubscriberAddress().toString());
        Assert.assertEquals("N4*KANSAS CITY*MO*64108~", subscriber.getSubscriberCityState().toString());
        Assert.assertEquals("PRV*RF*PXC*207Q00000X~", subscriber.getProviderInformation().toString());
        Assert.assertEquals("DMG*D8*19430917*M~", subscriber.getSubscriberDemographic().toString());
        Assert.assertEquals("INS*Y*18***************3~", subscriber.getMultipleBirthSequenceNumber().toString());
        Assert.assertEquals("HI*BK:8901*BF:87200*BF:5559~", subscriber.getSubscriberHealthCareDiagnosisCode().toString());
        Assert.assertEquals("DTP*281*D8*20051015~", subscriber.getSubscriberDate().get(0).toString());
        Assert.assertEquals("DTP*291*D8*20051015~", subscriber.getSubscriberDate().get(1).toString());
        Assert.assertEquals(2, subscriber.getSubscriberDate().size());

        for(int i = 0; i < 5; i++) {
            BenefitInquirySubscriberEligibility benefitInquirySubscriberEligibility = subscriber.getSubscriberEligibility().get(i);
            Assert.assertEquals("EQ*"+(30+i)+"**FAM~",
                    benefitInquirySubscriberEligibility.toString());
        }
        Assert.assertEquals(5, subscriber.getSubscriberEligibility().size());

        Assert.assertEquals(1, subscriber.getDependents().size());
        Assert.assertEquals(dependentString, subscriber.getDependents().get(0).toString());
        Assert.assertEquals("5", subscriber.getDependents().get(0).getDependentLevel().getHierarchicalParentIDNumber());
        Assert.assertEquals(5, subscriber.getDependents().get(0).getDependentEligibility().size());
    }
}