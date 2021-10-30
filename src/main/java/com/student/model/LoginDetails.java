package com.student.model;

import lombok.Data;

@Data
public class LoginDetails {
	
	private String userName;
	
	private String password;
	
	private String message;

	private String displayName;

	private String userID;

	private String firstName;

	private String lastName;

	private String accessToken;

	private String accessTokenChat;

	private String tokenCreated;

	private String clientLogo;

	private Integer clientId;

	private String clientName;

	private String organizationCode;

	private String theme;

	private String roleId;

	private String roleName;

	private Integer firstTimeLogin;

	private String profilePhoto;

	private String userImage;

	private Integer createGroupLimit;

	private Integer decimalPrecision;

	private String environmentName;

	private String email;

	private String userBuildingIDs;

	private String domain;

	private String languageCode;

	private String widgetsLayout;

	private String helpDeskContact;

	private String helpDeskEmail;

	private String helpDeskTimings;

	private String appTitle;

	private String versionNumber;

	private Integer themeID;

	private String themeName;

	private String themeContent;

	private Integer isDefault;

	private String fontName;

	private Boolean isUserExpiryWarningShow;

	private String refreshToken;

	private Boolean isUpateFeatures;

	private String sessionId;

	private Long sessionExpiryMillis = 0L;

	private Integer regionID;

}
