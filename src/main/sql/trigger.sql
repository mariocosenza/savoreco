SET SCHEMA 'savoreco';

create or replace function inserisci_in_basket()
    returns trigger as
$$
begin
    -- Inserisce un nuovo record in 'basket' con il 'user_id' dell'account appena creato
    insert into basket(user_id) values (NEW.user_id);
    return NEW;
end ;
$$ language plpgsql;


create trigger user_creation
    after insert
    on user_account
    for each row
execute function inserisci_in_basket();


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
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_food_quantity
    AFTER UPDATE OF quantity ON food
    FOR EACH ROW
    WHEN (NEW.quantity = 0)
EXECUTE FUNCTION update_food_availability();
