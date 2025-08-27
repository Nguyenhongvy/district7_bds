document.getElementById('contactForm').addEventListener('submit', function(e) {
    e.preventDefault(); // Ngăn form submit thông thường
    
    // Kiểm tra checkbox đồng ý điều khoản
    const agreeTerms = document.getElementById('agreeTerms');
    if (!agreeTerms.checked) {
        alert('Vui lòng đồng ý với điều khoản sử dụng và chính sách bảo mật.');
        return false;
    }
    
    // Lấy dữ liệu form
    const formData = new FormData(this);
    
    // Validate dữ liệu bắt buộc
    const fullName = formData.get('fullName');
    const phone = formData.get('phone');
    const email = formData.get('email');
    const message = formData.get('message');
    
    if (!fullName || !phone || !email || !message) {
        alert('Vui lòng điền đầy đủ các trường bắt buộc!');
        return false;
    }
    
    // Validate phone
    const phoneRegex = /^[0-9]{10,11}$/;
    if (!phoneRegex.test(phone.replace(/\s/g, ''))) {
        alert('Số điện thoại không hợp lệ!');
        return false;
    }
    
    // Validate email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Email không hợp lệ!');
        return false;
    }
    
    // Disable submit button
    const submitButton = this.querySelector('button[type="submit"]');
    const originalText = submitButton.innerHTML;
    submitButton.disabled = true;
    submitButton.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Đang gửi...';
    
    // Map các field theo đúng format mà ContactRequestController mong đợi
    const apiData = new FormData();
    apiData.append('fullName', fullName);
    apiData.append('phone', phone);
    apiData.append('email', email || ''); // Controller có required = false
    
    // Tạo message từ tất cả các field bổ sung
    let fullMessage = message;
    
    const subject = formData.get('subject');
    const budget = formData.get('budget');
    const location = formData.get('location');
    
    if (subject) {
        fullMessage += '\n\nChủ đề: ' + getSubjectText(subject);
    }
    if (budget) {
        fullMessage += '\nNgân sách: ' + getBudgetText(budget);
    }
    if (location) {
        fullMessage += '\nKhu vực quan tâm: ' + location;
    }
    
    apiData.append('message', fullMessage);
    apiData.append('requestType', 'CONSULTATION'); // Controller sẽ parse thành RequestType.CONSULTATION
    
    // projectId không có trong form contact này nên không cần append
    
    console.log('Sending contact request...');
    
    // Gửi AJAX request tới API endpoint có sẵn
    fetch(contextPath + '/api/contact/submit', {
        method: 'POST',
        body: apiData,
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => {
        console.log('Response status:', response.status);
        return response.json(); // Controller trả về JSON
    })
    .then(data => {
        console.log('Server response:', data);
        if (data.success) {
            // Hiện thông báo thành công
            showSuccessMessage(data.message || 'Cảm ơn bạn! Chúng tôi đã nhận được tin nhắn và sẽ liên hệ với bạn trong thời gian sớm nhất.');
            
            // Reset form
            this.reset();
        } else {
            showErrorMessage(data.message || 'Có lỗi xảy ra. Vui lòng thử lại!');
        }
    })
    .catch(error => {
        console.error('Fetch error:', error);
        showErrorMessage('Có lỗi xảy ra khi gửi tin nhắn. Vui lòng thử lại sau hoặc liên hệ trực tiếp qua hotline.');
    })
    .finally(() => {
        // Re-enable submit button
        submitButton.disabled = false;
        submitButton.innerHTML = originalText;
    });
});

// Helper functions - Di chuyển ra ngoài và định nghĩa đầy đủ
function getSubjectText(value) {
    const subjects = {
        'buy': 'Mua bán bất động sản',
        'rent': 'Cho thuê',
        'invest': 'Tư vấn đầu tư',
        'valuation': 'Định giá BDS',
        'legal': 'Tư vấn pháp lý',
        'other': 'Khác'
    };
    return subjects[value] || value;
}

function getBudgetText(value) {
    const budgets = {
        'under-2': 'Dưới 2 tỷ',
        '2-3': '2 - 3 tỷ',
        '3-5': '3 - 5 tỷ',
        '5-10': '5 - 10 tỷ',
        'over-10': 'Trên 10 tỷ'
    };
    return budgets[value] || value;
}

function showSuccessMessage(message) {
    // Tạo alert thành công động
    const alertHtml = `
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="fas fa-check-circle me-2"></i>
            <strong>Thành công!</strong> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;
    
    // Thêm vào đầu container
    const container = document.querySelector('.container.py-4');
    container.insertAdjacentHTML('afterbegin', alertHtml);
    
    // Auto scroll to top
    window.scrollTo({ top: 0, behavior: 'smooth' });
    
    // Auto remove after 5 seconds
    setTimeout(() => {
        const alert = document.querySelector('.alert-success');
        if (alert) {
            alert.remove();
        }
    }, 5000);
}

function showErrorMessage(message) {
    // Tạo alert lỗi động
    const alertHtml = `
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <i class="fas fa-exclamation-triangle me-2"></i>
            <strong>Lỗi!</strong> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;
    
    // Thêm vào đầu container
    const container = document.querySelector('.container.py-4');
    container.insertAdjacentHTML('afterbegin', alertHtml);
    
    // Auto scroll to top
    window.scrollTo({ top: 0, behavior: 'smooth' });
    
    // Auto remove after 8 seconds
    setTimeout(() => {
        const alert = document.querySelector('.alert-danger');
        if (alert) {
            alert.remove();
        }
    }, 8000);
}

// Phone number formatting
document.getElementById('phone').addEventListener('input', function(e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 11) {
        value = value.substring(0, 11);
    }
    e.target.value = value;
});