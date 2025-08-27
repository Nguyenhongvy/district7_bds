function removeFilter(filterName) {
    const url = new URL(window.location);
    url.searchParams.delete(filterName);
    window.location.href = url.toString();
}

function applySorting() {
    const sortValue = document.getElementById('sortSelect').value;
    const url = new URL(window.location);
    url.searchParams.set('sort', sortValue);
    window.location.href = url.toString();
}

function setViewMode(mode) {
    const gridBtn = document.getElementById('gridViewBtn');
    const listBtn = document.getElementById('listViewBtn');
    const projectsGrid = document.getElementById('projectsGrid');
    
    if (mode === 'list') {
        gridBtn.classList.remove('active');
        listBtn.classList.add('active');
        projectsGrid.classList.add('list-view');
    } else {
        listBtn.classList.remove('active');
        gridBtn.classList.add('active');
        projectsGrid.classList.remove('list-view');
    }
    
    // Save preference to localStorage
    localStorage.setItem('viewMode', mode);
}

// Restore view mode from localStorage
document.addEventListener('DOMContentLoaded', function() {
    const savedViewMode = localStorage.getItem('viewMode');
    if (savedViewMode) {
        setViewMode(savedViewMode);
    }
});

// Auto-submit filters on change
document.querySelectorAll('#typeFilter, #wardFilter, #statusFilter, #priceFilter').forEach(function(element) {
    element.addEventListener('change', function() {
        document.getElementById('filterForm').submit();
    });
});

// Search on Enter key
document.querySelector('input[name="search"]').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        document.getElementById('filterForm').submit();
    }
});