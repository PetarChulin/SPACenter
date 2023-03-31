clearAlerts = () => {
    setTimeout(() => {
        document.querySelectorAll('div.alert').forEach(d => d.style.display = 'none')
    }, 4000);
}