package unisinos.tradutores.java2python.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;


public class ScopeLevel {

    private Integer level;

    public ScopeLevel() {
        this.level = 0;
    }

    public Integer currentLevel() {
        return this.level;
    }

    public void up() {
        this.level++;
    }

    public void down() {
        this.level--;
    }
}
