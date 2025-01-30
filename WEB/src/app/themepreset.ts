import { definePreset } from '@primeng/themes';

import Lara from '@primeng/themes/lara';

const ThemePreset = definePreset(Lara, {
  semantic: {
    primary: {
      0: '#08A3D3',
      50: '#1B9EAA',
      100: '#269B97',
      200: '#37AF91',
      300: '#4DBD9D',
      400: '#91C612',
      500: '#B7D100',
      600: '#E8E000',
      700: '#FFD602',
      800: '#FFB400',
      900: '#F97A29',
      950: '#D12B49',
    },
    colorScheme: {
      light: {
        primary: {
          color: '{primary.400}',
          contrastColor: '#ffffff',
          hoverColor: '{primary.300}',
          activeColor: '{primary.0}',
        },
        // highlight: {
        //   color: '#000000',
        //   background: '#08a3d3',
        //   focusBackground: '#08a3d3',
        //   focusColor: '#e100ff',
        // },
        surface: {
          0: '#EEF6F4',
          50: '#DFE7E5',
          100: '#C4D6D4',
          200: '#B0C2BF',
          300: '#9BAFAD',
          400: '#8A9B99',
          500: '#788886',
          600: '#677A78',
          700: '#566B69',
          800: '#455C59',
          900: '#344D49',
          950: '#233E39',
        },
        contentBackground: '{surface.0}',
      },
      dark: {
        primary: {
          color: '{primary.400}',
          contrastColor: '{surface.900}',
          hoverColor: '{primary.3000}',
          activeColor: '{primary.0}',
        },
        // highlight: {
        //   color: '#000000',
        //   background: '#08a3d3',
        //   focusBackground: '#08a3d3',
        //   focusColor: '#e100ff',
        // },
        surface: {
          0: '#ffffff',
          50: '#e6e6e8',
          100: '#cccccf',
          200: '#b3b3b5',
          300: '#99999c',
          400: '#808083',
          500: '#66666a',
          600: '#4d4d51',
          700: '#333336',
          800: '#29292c',
          900: '#252528',
          950: '#222224',
        },
        contentBackground: '{surface.700}',
      },
    },
  },
  components: {
    inputtext: {
      root: {
        focusRing: {
          width: '0',
          style: 'none',
          color: 'transparent',
          offset: '0',
          shadow: 'none',
        },
        borderColor: '{primary.0}',
        borderRadius: '5px',
        placeholderColor: '{primary.0}',
        color: '{primary.0}',
      },
      colorScheme: {
        light: {
          root: {
            filledBackground: '{surface.0}',
            filledFocusBackground: '{surface.50}',
          },
        },
        dark: {
          root: {
            filledBackground: '{surface.700}',
            filledFocusBackground: '{surface.900}',
          },
        },
      },
    },
    button: {
      root: {
        paddingX: '0',
        paddingY: '0',
      },
    },
    textarea: {
      root: {
        focusRing: {
          width: '0',
          style: 'none',
          color: 'transparent',
          offset: '0',
          shadow: 'none',
        },
        borderColor: '{primary.0}',
        borderRadius: '5px',
        placeholderColor: '{primary.0}',
        color: '{primary.0}',
      },
      colorScheme: {
        light: {
          root: {
            filledBackground: '{surface.0}',
            filledFocusBackground: '{surface.50}',
          },
        },
        dark: {
          root: {
            filledBackground: '{surface.700}',
            filledFocusBackground: '{surface.900}',
          },
        },
      },
    },
    fileupload: {
      header: {
        borderWidth: '3px',
        borderColor: '{primary.0}',
        borderRadius: '5px',
        padding: '1rem',
      },
      colorScheme: {
        light: {
          header: {
            background: '{surface.0}',
          },
        },
        dark: {
          header: {
            background: '{surface.700}',
          },
        },
      },
    },
  },
});

export default ThemePreset;
