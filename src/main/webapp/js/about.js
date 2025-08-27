// Animate numbers on scroll
function animateNumbers() {
	const observers = [];
	const numberElements = document.querySelectorAll('.stat-number');

	numberElements.forEach((element) => {
		const observer = new IntersectionObserver((entries) => {
			entries.forEach((entry) => {
				if (entry.isIntersecting) {
					const target = parseInt(element.textContent.replace(/[^\d]/g, ''));
					const increment = target / 50;
					let current = 0;

					const timer = setInterval(() => {
						current += increment;
						if (current >= target) {
							current = target;
							clearInterval(timer);
						}
						element.textContent = Math.floor(current).toLocaleString() + '+';
					}, 40);

					observer.unobserve(entry.target);
				}
			});
		});

		observer.observe(element);
		observers.push(observer);
	});
}

// Initialize animations when page loads
document.addEventListener('DOMContentLoaded', function() {
	animateNumbers();

	// Add smooth scrolling to all links
	document.querySelectorAll('a[href^="#"]').forEach(anchor => {
		anchor.addEventListener('click', function(e) {
			e.preventDefault();
			const target = document.querySelector(this.getAttribute('href'));
			if (target) {
				target.scrollIntoView({
					behavior: 'smooth',
					block: 'start'
				});
			}
		});
	});

	// Add animation class to timeline items on scroll
	const timelineItems = document.querySelectorAll('.timeline-item');
	const timelineObserver = new IntersectionObserver((entries) => {
		entries.forEach((entry) => {
			if (entry.isIntersecting) {
				entry.target.style.opacity = '1';
				entry.target.style.transform = 'translateY(0)';
			}
		});
	});

	timelineItems.forEach((item) => {
		item.style.opacity = '0';
		item.style.transform = 'translateY(50px)';
		item.style.transition = 'all 0.6s ease';
		timelineObserver.observe(item);
	});
});