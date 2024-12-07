
let menu = document.querySelector('.header .menu');

document.querySelector('#menu-btn').onclick = () =>{
   menu.classList.toggle('active');
}

window.onscroll = () =>{
   menu.classList.remove('active');
}

document.querySelectorAll('input[type="number"]').forEach(inputNumber => {
   inputNumber.oninput = () =>{
      if(inputNumber.value.length > inputNumber.maxLength) inputNumber.value = inputNumber.value.slice(0, inputNumber.maxLength);
   };
});

document.addEventListener('DOMContentLoaded', function () {
   const fileInput = document.querySelector('input[type="file"]');
   if (fileInput) {
      fileInput.addEventListener('change', function () {
         const fileList = this.files;
         if (fileList.length > 0) {
            console.log(`Selected files: ${fileList.length}`);
            Array.from(fileList).forEach(file => {
               console.log(file.name);
            });
         }
      });
   }
});



document.addEventListener('DOMContentLoaded', function() {
   document.querySelectorAll('.view-property .details .thumb .small-images img').forEach(image => {
      image.onclick = () => {
         // Get the clicked image's source and update the big image
         document.querySelector('.view-property .details .thumb .big-image img').src = image.getAttribute('src');
      };
   });
});


document.querySelectorAll('.faq .box-container .box h3').forEach(headings =>{
   headings.onclick = () =>{
      headings.parentElement.classList.toggle('active');
   }
});
function toggleFeedbackRequest() {
   const checkbox = document.getElementById('is-public-checkbox');
   const feedbackLabel = document.getElementById('feedback-label');
   const feedbackInput = document.getElementById('feedback-request');

   if (checkbox.checked) {
      // Hide feedback elements when "Is Public" is checked
      feedbackLabel.style.display = 'none';
      feedbackInput.style.display = 'none';
   } else {
      // Show feedback elements when "Is Public" is unchecked
      feedbackLabel.style.display = 'inline';
      feedbackInput.style.display = 'inline';
   }
}

// Set initial state on page load based on the checkbox's initial value
window.onload = function () {
   toggleFeedbackRequest();
};