// check for saved 'darkMode' in localStorage
let darkTheme = localStorage.getItem('darkTheme'); 

const darkThemeToggle = document.querySelector('#dark-theme-toggle');

const enableDarkTheme = () => {
  // 1. Add the class to the body
  document.body.classList.add('darkTheme');
  // 2. Update darkMode in localStorage
  localStorage.setItem('darkTheme', 'enabled');
}

const disableDarkTheme = () => {
  // 1. Remove the class from the body
  document.body.classList.remove('darkTheme');
  // 2. Update darkMode in localStorage 
  localStorage.setItem('darkTheme', null);
}
 
// If the user already visited and enabled darkMode
// start things off with it on
if (darkTheme === 'enabled') {
  enableDarkTheme();
}

// When someone clicks the button
darkThemeToggle.addEventListener('click', () => {
  // get their darkMode setting
  darkTheme = localStorage.getItem('darkTheme'); 
  
  // if it not current enabled, enable it
  if (darkTheme !== 'enabled') {
    enableDarkTheme();
  // if it has been enabled, turn it off  
  } else {  
    disableDarkTheme(); 
  }
});
