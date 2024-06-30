function autocomplete() {
    Radar.initialize('prj_live_pk_bc9bb756fa7f36a4b5b2ce09cd0c587f58ada95c');

// create autocomplete input
    Radar.ui.autocomplete({
        container: 'autocomplete',
        showMarkers: true,
        markerColor: '#ACBDC8',
        responsive: true,
        minWidth: '100%',
        countryCode: 'IT',
        maxHeight: '50vh',
        placeholder: 'Inserisci indirizzo',
        limit: 8,
        minCharacters: 3,
        onSelection: (address) => {
            window.location.href = `/search?lat=${address.latitude}&lon=${address.longitude}`;
        },
    });
}
