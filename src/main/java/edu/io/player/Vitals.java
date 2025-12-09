package edu.io.player;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Vitals {

    // zmienna poziomu nawodnienia
    private int hydration;

    // wywoływana w momencie śmierci (hydration == 0)
    private Runnable onDeathCallback;

    public Vitals() {
        this.hydration = 100; // Startujemy w pełni nawodnieni [cite: 88]

        // Inicjalizujemy pustą lambdą, aby uniknąć NullPointerException
        this.onDeathCallback = () -> {};
    }

    public int hydration() {
        return hydration;
    }

    public boolean isAlive() {
        return hydration > 0; // [cite: 45]
    }

    // Metoda do nawadniania
    public void hydrate(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Nie można nawodnić ujemną wartością");
        this.hydration += amount;
        if (this.hydration > 100) {
            this.hydration = 100;
        }
    }

    // odwodnienie
    public void dehydrate(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Nie można odwodnić ujemną wartością");

        // Zmniejszamy nawodnienie, ale nie poniżej zera
        this.hydration -= amount;
        if (this.hydration < 0) {
            this.hydration = 0;
        }

        // jeśli nawodnienie będzie równe zeru aktywuje się callback
        if (this.hydration == 0) {
            onDeathCallback.run();
        }
    }

    // Reakcja na śmierć gracza
    public void setOnDeathHandler(@NotNull Runnable callback) {
        // Fail fast
        this.onDeathCallback = Objects.requireNonNull(callback, "Callback cannot be null");
    }
}