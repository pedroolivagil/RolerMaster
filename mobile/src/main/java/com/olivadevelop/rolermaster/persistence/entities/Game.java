package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "GAME")
public class Game extends BasicEntity {

    public enum GameStatus {
        DRAFT(0), CREATED(1), STARTED(2), PAUSED(3), FINISHED(4);

        private Integer val;

        GameStatus(Integer val) {
            this.val = val;
        }

        public Integer val() {
            return val;
        }

        public GameStatus find(Integer val) {
            GameStatus retorno = DRAFT;
            if (DRAFT.val().equals(val)) {
                retorno = DRAFT;
            } else if (CREATED.val().equals(val)) {
                retorno = CREATED;
            } else if (STARTED.val().equals(val)) {
                retorno = STARTED;
            } else if (PAUSED.val().equals(val)) {
                retorno = PAUSED;
            } else if (FINISHED.val().equals(val)) {
                retorno = FINISHED;
            }
            return retorno;
        }
    }

    private Integer idGame;
    private String name;
    private String code;
    private Integer maxCharacters;
    private Date date;
    private Resource headerResource;
    private Resource locationResource;
    private List<Integer> resources;
    private GameStatus gameStatus;
    private String history;
    private String location;
    @RelatedEntity
    private GameCategory gameCategory;

    public Game() {
    }

    public Game(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    private void setCode() {
        this.code = generateCode();
    }

    public Integer getMaxCharacters() {
        return maxCharacters;
    }

    public void setMaxCharacters(Integer maxCharacters) {
        this.maxCharacters = maxCharacters;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Resource getHeaderResource() {
        return headerResource;
    }

    public void setHeaderResource(Resource headerResource) {
        this.headerResource = headerResource;
    }

    public Resource getLocationResource() {
        return locationResource;
    }

    public void setLocationResource(Resource locationResource) {
        this.locationResource = locationResource;
    }

    public List<Integer> getResources() {
        return resources;
    }

    public void setResources(List<Integer> resources) {
        this.resources = resources;
    }

    public GameStatus getGameStatus() {
        if (Tools.isNull(gameStatus)) {
            gameStatus = GameStatus.DRAFT;
        }
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    @Override
    public String generateCode() {
        StringBuilder codeBuilder = new StringBuilder();
        if (Tools.isNotNull(getName())) {
            String tempName = getName().trim();
            if (tempName.length() > 10) {
                tempName = tempName.replace(" ", "").substring(0, 10);
            } else {
                if (tempName.length() < 10) {
                    tempName = Tools.fillStringByBeforeChar(tempName, Tools.generateID(1), 10);
                }
            }
            codeBuilder.append(tempName);
        } else {
            codeBuilder.append(Tools.generateID(10));
        }
        codeBuilder.append(Tools.getCurrentDate("yyyyMMdd"));
        return codeBuilder.toString();
    }
}
