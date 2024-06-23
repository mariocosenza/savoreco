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
execute procedure inserisci_in_basket();