package com.rapipay.otp.app.Services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rapipay.otp.app.OTP;
import com.rapipay.otp.app.OTPRepository;

//@RunWith(SpringRunner.class)
@SpringBootTest
class OTPServiceTest {
	
	@Autowired
	private OTPService otpService;
	
	@MockBean
	private OTPRepository dao;

	@Test
	@DisplayName("Validate Email 1")
	void testCheckEmail1() {
		//fail("Not yet implemented");
		OTPService otpService=new OTPService();
	    boolean expected=true;
	    boolean actual=otpService.checkEmail("abc@gmail.com");
	    assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("Validate Email 2")
	void testCheckEmail2() {
		//fail("Not yet implemented");
		OTPService otpService=new OTPService();
	    boolean expected=false;
	    boolean actual=otpService.checkEmail("abc@gmail.");
	    assertEquals(expected,actual);
	}
	OTP inputotp=new OTP(51,"email","mayank.1822cs1081@kiet.edu",false);
	@Test
	void testSendOTP() {
		//List<OTP>checkEmail=dao.findByEmailAndVerified("mayank.1822cs1081@kiet.edu", false);
		OTP otp=new OTP();
		otp.setEmail(inputotp.getEmail());
        otp.setOrderId();
        otp.setChannelName(inputotp.getChannelName());
        otp.setPhoneNo(inputotp.getPhoneNo());
        otp.setOtp(otpService.generateOTP());
        otp.setVerified(false);
        otp.setCreated_at(new Date().getTime() /1000);
        dao.saveAndFlush(otp);
        
		String actual=otpService.sendOTP(otp);
		String expected="successfully";
		assertEquals(expected,actual);
	}

	@Test
	void testVerifyOTP() {
		//fail("Not yet implemented");
		
		OTP otp=new OTP("mayank.1822cs1081@kiet.edu",389383,0,false);
		
		assertThrows(Exception.class, ()->{
			otpService.verifyOTP(otp);
	});
		}
	@Test
	void testVerifyOTP2() {
		//fail("Not yet implemented");
		
		OTP otp=new OTP("mayank.1822cs1083@kiet.edu",335556,0,false);
//		String actual=otpService.verifyOTP(otp);
//		String expected="OTP verified successfully";
//		assertEquals(expected,actual);
		
		assertThrows(Exception.class, ()->{
			otpService.verifyOTP(otp);
	});
		}
	@Test
	void testSendOTPSMS() {
//		OTPService otpService=new OTPService();
		OTP otp=new OTP(51,"sms","9897630233");

		String actual=otpService.sendOTPSMS(otp);
		String expected="successfully";
		assertEquals(expected,actual);
	}
}
