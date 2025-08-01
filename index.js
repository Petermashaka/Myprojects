const container=document.querySelector('.container');
const loginLink=document.querySelector('.signInLink');
const registerLink=document.querySelector('.signUpLink');
registerLink.addEventListener('click',()=>{
    container.classList.add('active');
});