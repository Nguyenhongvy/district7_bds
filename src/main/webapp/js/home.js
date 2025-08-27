function performQuickSearch() {
    const type = document.getElementById('quickSearchType').value;
    const ward = document.getElementById('quickSearchWard').value;
    const price = document.getElementById('quickSearchPrice').value;
    
	let searchUrl = contextPath + '/projects?';
    const params = [];
    
    if (type) params.push('type=' + encodeURIComponent(type));
    if (ward) params.push('ward=' + encodeURIComponent(ward));
    if (price) params.push('price=' + encodeURIComponent(price));
    
    if (params.length > 0) {
        searchUrl += params.join('&');
    }
    
    window.location.href = searchUrl;
}

// Enter key support for search
document.addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        performQuickSearch();
    }
});