SET SCHEMA 'savoreco';

CREATE OR REPLACE FUNCTION inserisci_in_purchase()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.pick_up IS FALSE AND (NEW.street IS NULL OR NEW.zipcode IS NULL) THEN
        RAISE EXCEPTION 'must enter address';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER purchase
    BEFORE INSERT
    ON purchase
    FOR EACH ROW
EXECUTE FUNCTION inserisci_in_purchase();


CREATE OR REPLACE FUNCTION update_food_availability()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.quantity = 0 THEN
        NEW.available := false;
    ELSE
        NEW.available := true;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_food_quantity
    BEFORE UPDATE OF quantity ON food
    FOR EACH ROW
EXECUTE FUNCTION update_food_availability();