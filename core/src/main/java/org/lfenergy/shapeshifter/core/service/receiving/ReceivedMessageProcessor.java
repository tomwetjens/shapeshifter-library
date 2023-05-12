// Copyright 2023 Contributors to the Shapeshifter project
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.shapeshifter.core.service.receiving;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lfenergy.shapeshifter.api.PayloadMessageType;
import org.lfenergy.shapeshifter.api.SignedMessage;
import org.lfenergy.shapeshifter.core.model.UftpParticipant;
import org.lfenergy.shapeshifter.core.service.UftpErrorProcessor;
import org.lfenergy.shapeshifter.core.service.handler.UftpPayloadHandler;
import org.lfenergy.shapeshifter.core.service.receiving.DuplicateMessageDetection.DuplicateMessageResult;

@Slf4j
@RequiredArgsConstructor
public class ReceivedMessageProcessor {

  private final UftpPayloadHandler payloadHandler;
  private final DuplicateMessageDetection duplicateDetection;
  private final UftpErrorProcessor errorProcessor;

  public void onReceivedMessage(SignedMessage signedMessage, PayloadMessageType request) {
    var sender = new UftpParticipant(signedMessage);
    log.debug("Processing of received {} message from {}", request.getClass().getSimpleName(), sender);

    if (isDuplicateMessage(sender, request)) {
      throw new DuplicateMessageException(
          "Received " + request.getClass().getSimpleName() + " with MessageID '" + request.getMessageID() + "' from " + sender + " is a duplicate and has already been received.");
    }

    payloadHandler.notifyNewIncomingMessage(sender, request);
  }

  private boolean isDuplicateMessage(UftpParticipant sender, PayloadMessageType payloadMessage) {
    var duplicate = duplicateDetection.isDuplicate(payloadMessage) != DuplicateMessageResult.NEW_MESSAGE;
    if (duplicate) {
      log.info("Received message {} {} from {} is a duplicate and has already been processed. It will not be submitted to the application.",
               payloadMessage.getClass(), payloadMessage.getMessageID(), sender);

      errorProcessor.onDuplicateReceived(sender, payloadMessage);
    }
    return duplicate;
  }
}