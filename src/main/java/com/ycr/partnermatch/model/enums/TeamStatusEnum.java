package com.ycr.partnermatch.model.enums;

/**
 * 队伍状态枚举
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/27 19:14
 */
public enum TeamStatusEnum {
	PUBLIC(0, "公开"),
	PRIVATE(1, "私有"),
	ENCRYPT(2, "加密");

	private int value;
	private String text;

	public static TeamStatusEnum getEnumByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (TeamStatusEnum teamStatusEnum : values()) {
			if (teamStatusEnum.getValue() == value) {
				return teamStatusEnum;
			}
		}
		return null;
	}

	TeamStatusEnum(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

}
