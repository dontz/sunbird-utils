package org.sunbird.common.request.badge;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.sunbird.common.exception.ProjectCommonException;
import org.sunbird.common.models.util.JsonKey;
import org.sunbird.common.request.Request;
import org.sunbird.common.responsecode.ResponseCode;

/**
 * Test class for Badge assertion request data.
 * @author Manzarul
 *
 */
public class BadgeAssertionTest {
	
	
	@Test
	public void validateAssertionSuccess () {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "slug12");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			Assert.assertNull(e);
		}
		assertEquals(true, response);
	}

	@Test
	public void validateAssertionWithEmptyIssuer () {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.issuerSlugRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
	
	@Test
	public void validateAssertionWithOutIssuer () {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.issuerSlugRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
	
	@Test
	public void validateAssertionWithEmptyBadgeSlug () {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "issuerSlug");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.badgeSlugRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
   
	@Test
	public void validateAssertionWithOutBadgeSlug () {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "issuerSlug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.badgeSlugRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
	
	@Test
	public void validateAssertionWithEmptyRecipient () {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "issuerSlug");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.recipientEmailRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
	
	@Test
	public void assertionWithInvalidRecipient() {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "issuerSlug");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul");
		requestObj.put(JsonKey.EVIDENCE, "http://localhost:8000/public/badges/db-design-expert");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.emailFormatError.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
	
	@Test
	public void assertionWithOutEvidence() {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "issuerSlug");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.evidenceRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
	
	@Test
	public void assertionWithEmptyEvidence() {
		Request request = new Request();
		boolean response = false;
		Map<String, Object> requestObj = new HashMap<>();
		requestObj.put(JsonKey.ISSUER_SLUG, "issuerSlug");
		requestObj.put(JsonKey.BADGE_CLASS_SLUG, "classslug");
		requestObj.put(JsonKey.RECIPIENT_EMAIL, "manzarul.haque@tarento.com");
		requestObj.put(JsonKey.EVIDENCE, "  ");
		requestObj.put(JsonKey.NOTIFY, false);
		request.setRequest(requestObj);
		try {
			BadgeAssertionValidator.validateBadgeAssertion(request);
			response = true;
		} catch (ProjectCommonException e) {
			assertEquals(ResponseCode.CLIENT_ERROR.getResponseCode(), e.getResponseCode());
			assertEquals(ResponseCode.evidenceRequired.getErrorCode(), e.getCode());
		}
		assertEquals(false, response);
	}
}
