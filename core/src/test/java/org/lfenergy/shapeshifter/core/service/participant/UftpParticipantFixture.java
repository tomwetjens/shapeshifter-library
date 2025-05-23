// Copyright 2023 Contributors to the Shapeshifter project
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.shapeshifter.core.service.participant;

import org.lfenergy.shapeshifter.api.USEFRoleType;
import org.lfenergy.shapeshifter.core.model.UftpParticipant;

public class UftpParticipantFixture {

  public static final String AGR_DOMAIN = "arg.domain.eu";
  public static final String DSO_DOMAIN = "dso.domain.eu";

  public static final UftpParticipant AGR = new UftpParticipant(AGR_DOMAIN, USEFRoleType.AGR);
  public static final UftpParticipant DSO = new UftpParticipant(DSO_DOMAIN, USEFRoleType.DSO);


}