package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.*;
import org.kursovoi.server.model.Card;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.CardRepository;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.mapper.CardMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final CardMapper mapper;

    @Transactional
    public List<CardDto> getCardsOfAccount(long idAccount) {
        var account = accountService.getSpecificAccount(idAccount);
        return cardRepository.findByAccount(account).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public CardDto getSpecificCardDto(long idCard) {
        return mapper.map(getSpecificCard(idCard));
    }

    @Transactional
    public void createCard(CreateCardDto dto) {
        var newCard = mapper.map(dto);
        newCard.setCvv(Double.toString(Math.floor(Math.random() * 900 + 100)));
        newCard.setStatus(Status.PENDING);
        newCard.setAccount(accountService.getSpecificAccount(dto.getIdAccount()));
        newCard.setPin("CHANGE_ME");
        cardRepository.save(newCard);
    }

    @Transactional
    public void activateCard(ActivateCardDto dto) {
        var card = getSpecificCard(dto.getCardId());
        card.setPin(dto.getNewPin());
        card.setStatus(Status.ACTIVE);
        cardRepository.save(card);
    }

    @Transactional
    public void changeStatusOfCard(ChangeStatusOfCardDto dto) {
        if (Arrays.stream(Status.values()).map(Enum::name).noneMatch(status -> status.equals(dto.getNewStatus()))) {
            throw new IncorrectStatusException("Status is incorrect");
        }
        var card = getSpecificCard(dto.getCardId());
        card.setStatus(Status.valueOf(dto.getNewStatus()));
        cardRepository.save(card);
    }

    @Transactional
    Card getSpecificCard(long idCard) {
        return cardRepository.findById(idCard)
                .orElseThrow(() -> new ModelNotFoundException("Card with id: " + idCard + " - not found!"));
    }


}
